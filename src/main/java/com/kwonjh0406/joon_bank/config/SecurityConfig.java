package com.kwonjh0406.joon_bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/","/api/user/**" , "/login", "/signup", "/css/**", "/js/**").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/my/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
        );

        http.formLogin((auth) -> auth
                .loginPage("/login") // 권한이 없는 페이지 접근 시 /login 리다이렉트
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home")
                .usernameParameter("username")			// 아이디 파라미터명 설정
                .passwordParameter("password")			// 패스워드 파라미터명 설정
                .permitAll()
        );

        http.csrf((auth) -> auth.disable());

        return http.build();
    }

    public void configure(HttpSecurity http) throws Exception {

    }
}
