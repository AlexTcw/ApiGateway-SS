package com.api.gateway.controller;

import com.api.gateway.model.dto.consume.ConsumeJsonLogin;
import com.api.gateway.model.dto.consume.ConsumeJsonString;
import com.api.gateway.model.dto.response.ResponseJsonLogin;
import com.api.gateway.model.dto.response.ResponseJsonString;
import com.api.gateway.service.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/auth"})
public class AuthController {

    private final UserService userService;

    @PostMapping(value = {"/login"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseJsonLogin> login(@RequestBody ConsumeJsonLogin consume) {
        return ResponseEntity.ok(userService.login(consume));
    }

    @PostMapping(value = {"/bcrypt"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseJsonString> bcrypt(@RequestBody ConsumeJsonString consume) {
        return ResponseEntity.ok(userService.bcrypt(consume));
    }
}
