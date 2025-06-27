package com.api.gateway.model.dto.response;

import java.util.Set;

public record ResponseJsonLogin(long userId, Set<String> permissions, Set<String> roles, String username, String jwtToken) {
}
