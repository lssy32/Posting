package com.example.posting.security;

import com.example.posting.jwt.JwtAuthFilter;
import com.example.posting.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfiguration{
    private final JwtUtil jwtUtil;
    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.headers().frameOptions().disable(); // 해당 페이지를 <frame> 또는<iframe>, <object> 에서 렌더링할 수 있는지 여부

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션이 필요하면 생성하도록 셋팅


        http.authorizeHttpRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/users/login").permitAll()
                .antMatchers("/api/users/signup").permitAll()
//                .antMatchers("/api/users/**").hasRole("CUSTOMER")
//                .antMatchers("/api/sellers/**").hasRole("SELLER")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
