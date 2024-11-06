package com.kwonjh0406.joon_bank.domain.user.service;

import com.kwonjh0406.joon_bank.domain.user.dto.SignUpParam;
import com.kwonjh0406.joon_bank.domain.user.entity.User;
import com.kwonjh0406.joon_bank.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> signUp(SignUpParam signUpParam) {

        if(userRepository.existsByUsername(signUpParam.getUsername())) {
            return ResponseEntity.badRequest().body("이미 가입된 아이디입니다.");
        }

        if(userRepository.existsByPhoneNumber(signUpParam.getPhoneNumber())) {
            return ResponseEntity.badRequest().body("이미 가입된 전화번호입니다.");
        }

        User user = User.builder()
                .name(signUpParam.getName())
                .phoneNumber(signUpParam.getPhoneNumber())
                .username(signUpParam.getUsername())
                .password(passwordEncoder.encode(signUpParam.getPassword()))
                .build();

        userRepository.save(user);

        return ResponseEntity.ok("회원가입 성공");
    }
}