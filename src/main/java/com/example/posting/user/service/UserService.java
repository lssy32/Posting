package com.example.posting.user.service;

import com.example.posting.user.dto.SignupRequestDto;
import com.example.posting.user.entity.User;
import com.example.posting.user.entity.UserRoleEnum;
import com.example.posting.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final String adminKey = "123123123";
    private final UserRepository userRepository;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto){
        UserRoleEnum userRoleEnum = UserRoleEnum.USER;
        if (signupRequestDto.getAdmin()){
            if (signupRequestDto.getAdminKey().equals(adminKey)) {
            userRoleEnum = UserRoleEnum.ADMIN;
            } else {
                throw new IllegalArgumentException("어드민 키값이 다릅니다.");
            }
        }
        User user = new User(signupRequestDto.getUserId(), signupRequestDto.getPassword(), userRoleEnum);
        userRepository.save(user);
    }
}
