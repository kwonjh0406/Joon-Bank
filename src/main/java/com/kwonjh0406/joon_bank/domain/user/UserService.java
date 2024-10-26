package com.kwonjh0406.joon_bank.domain.user;

import com.kwonjh0406.joon_bank.domain.user.dto.SignUpParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(SignUpParam signUpParam) {
        User user = User.builder()
                .name(signUpParam.getName())
                .phoneNumber(signUpParam.getPhoneNumber())
                .username(signUpParam.getUsername())
                .password(signUpParam.getPassword())
                .build();

        userRepository.save(user);
    }

}
