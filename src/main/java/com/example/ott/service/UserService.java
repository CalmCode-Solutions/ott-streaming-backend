package com.example.ott.service;


import com.example.ott.dto.response.UserResponse;
import com.example.ott.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

     User getByEmail(String email);

     UserResponse me(String email);

     Page<UserResponse> list(Pageable pageable);
}
