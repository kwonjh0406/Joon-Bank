package com.kwonjh0406.joon_bank.domain.user;

import com.kwonjh0406.joon_bank.domain.user.dto.SignUpParam;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void signUp(@RequestBody SignUpParam signUpParam) {
        userService.signUp(signUpParam);
    }

}
