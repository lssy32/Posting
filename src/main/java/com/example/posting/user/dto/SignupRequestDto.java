package com.example.posting.user.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String userId;
    private String password;
    private Boolean admin;
    private String adminKey;
}
