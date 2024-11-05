package com.kwonjh0406.joon_bank.domain.user.controller;

import com.kwonjh0406.joon_bank.domain.user.UserService;
import com.kwonjh0406.joon_bank.domain.user.dto.SignUpParam;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    @Autowired
    UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpParam signUpParam) {

//        System.out.println("signUpParam name: " + signUpParam.getName());
//        System.out.println("signUpParam phoneNumber: " + signUpParam.getPhoneNumber());
//        System.out.println("signUpParam username: " + signUpParam.getUsername());
//        System.out.println("signUpParam password: " + signUpParam.getPassword());

        return userService.signUp(signUpParam);
    }

}
