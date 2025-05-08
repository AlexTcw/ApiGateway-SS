package com.api.gateway.model.dto.response;

public record ResponseJsonLogin(long userId, String username, String jwtToken) {
}
