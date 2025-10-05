package com.api.gateway.repository;

import com.api.gateway.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    @Query(value = """
                    select r.name,p.name
                    from usuarios.usuario u
                    inner join usuarios.user_role ur on u.user_id = ur.user_id
                    inner join usuarios.role r on ur.role_id = r.role_id
                    inner join usuarios.rol_permission rp on r.role_id = rp.role_id
                    inner join usuarios.permission p on rp.permission_id = p.permission_id
                    where u.user_id = :user_id
                    """,nativeQuery = true)
    List<Object[]> findAllUserRolesAndPermissionsByUserId(@Param("user_id") Long userId);

}
