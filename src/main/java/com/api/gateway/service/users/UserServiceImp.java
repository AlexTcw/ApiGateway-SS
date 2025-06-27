package com.api.gateway.service.users;

import com.api.gateway.model.dto.consume.ConsumeJsonLogin;
import com.api.gateway.model.dto.consume.ConsumeJsonString;
import com.api.gateway.model.dto.response.ResponseJsonLogin;
import com.api.gateway.model.dto.response.ResponseJsonString;
import com.api.gateway.model.entity.User;
import com.api.gateway.model.exception.ResourceNotFoundException;
import com.api.gateway.repository.UserRepository;
import com.api.gateway.service.JWT.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authManager;

    @Override
    public String bcrypt(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public ResponseJsonString bcrypt(ConsumeJsonString consume) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return new ResponseJsonString(passwordEncoder.encode(consume.key()));
    }

    @Override
    public ResponseJsonLogin login(ConsumeJsonLogin consume) {
        User user = userRepository.findUserByUsername(consume.username());
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        String jwtToken = authenticate(consume.username(), consume.password())
                .orElseThrow(() -> new RuntimeException("Authentication failed"));


        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();

        List<Object []> rolPermissionUser = userRepository.findAllUserRolesAndPermissionsByUserId(user.getUserId());
        rolPermissionUser.forEach(rp -> {
            roles.add(rp[0].toString());
            permissions.add(rp[1].toString());
        });

        return new ResponseJsonLogin(
                user.getUserId(),
                permissions,
                roles,
                user.getUsername(),
                jwtToken
        );
    }

    private Optional<String> authenticate(String username, String password) {
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return Optional.of(jwtService.getToken(userDetails));
        } catch (AuthenticationException e) {
            return Optional.empty();
        }
    }

}
