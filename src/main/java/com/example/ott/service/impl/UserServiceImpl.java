package com.example.ott.service.impl;

import com.example.ott.dto.response.UserResponse;
import com.example.ott.entity.User;
import com.example.ott.exception.ResourceNotFoundException;
import com.example.ott.repository.UserRepository;
import com.example.ott.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserResponse me(String email) {
        return UserResponse.from(getByEmail(email));
    }

    public Page<UserResponse> list(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserResponse::from);
    }
}

