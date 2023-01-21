package com.example.posting.user.controller;

import com.example.posting.user.dto.SignupRequestDto;
import com.example.posting.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup (@RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        return new ResponseEntity("회원가입 성공", HttpStatus.OK);
    }
}
