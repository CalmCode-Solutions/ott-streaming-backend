package com.example.ott.dto.response;

import com.example.ott.entity.Role;
import com.example.ott.entity.User;

public record UserResponse(Long id, String fullName, String email, Role role) {

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getFullName(), user.getEmail(), user.getRole());
    }
}
