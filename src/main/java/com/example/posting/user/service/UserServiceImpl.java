package com.example.posting.user.service;

import com.example.posting.jwt.JwtUtil;
import com.example.posting.user.dto.LoginRequestDto;
import com.example.posting.user.dto.SignupRequestDto;
import com.example.posting.user.entity.User;
import com.example.posting.user.entity.UserRoleEnum;
import com.example.posting.user.repository.UserRepository;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final String adminKey = "123123123";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    @Override
    public void signup(SignupRequestDto signupRequestDto){
        UserRoleEnum userRoleEnum = UserRoleEnum.USER;
        if (signupRequestDto.getAdmin()){
            if (signupRequestDto.getAdminKey().equals(adminKey)) {
            userRoleEnum = UserRoleEnum.ADMIN;
            } else {
                throw new IllegalArgumentException("어드민 키값이 다릅니다.");
            }
        }
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        User user = new User(signupRequestDto.getUserId(), password, userRoleEnum);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response){
        String userId = loginRequestDto.getUserId();
        String password = passwordEncoder.encode(loginRequestDto.getPassword());
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        if (!user.getUserPassword().equals(password)){
            new IllegalArgumentException("비밀번호가 다릅니다.");
        }
        response.addHeader(jwtUtil.AUTHORIZATION_HEADER,jwtUtil.createToken(userId, user.getRole()));
    }
}
