package com.api.gateway.model.entity;

import com.api.gateway.model.enums.PermissionEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permission")
public class Permission implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "permission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true, updatable = false)
    private PermissionEnum name;
}
