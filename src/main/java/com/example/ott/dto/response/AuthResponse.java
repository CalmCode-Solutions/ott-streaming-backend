package com.example.ott.dto.response;

public record AuthResponse(
        String accessToken,
        String refreshToken,
        long expiresInSeconds,
        UserResponse user) {
}
