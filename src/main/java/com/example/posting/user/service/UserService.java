package com.example.posting.user.service;

import com.example.posting.user.dto.LoginRequestDto;
import com.example.posting.user.dto.SignupRequestDto;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
    void signup(SignupRequestDto signupRequestDto);
    void login(LoginRequestDto loginRequestDto, HttpServletResponse response);
}
