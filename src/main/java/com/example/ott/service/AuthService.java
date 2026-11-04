package com.example.ott.service;

import com.example.ott.dto.request.LoginRequest;
import com.example.ott.dto.request.RefreshRequest;
import com.example.ott.dto.request.RegisterRequest;
import com.example.ott.dto.response.AuthResponse;


public interface AuthService {

     AuthResponse register(RegisterRequest request) ;


     AuthResponse login(LoginRequest request);


     AuthResponse refresh(RefreshRequest request);

}
