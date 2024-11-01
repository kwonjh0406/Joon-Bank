package com.kwonjh0406.joon_bank.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpParam {

    @NotBlank(message = "이름을 입력해주세요.")
    @Pattern(regexp = "^[가-힣]*$",
            message = "이름을 다시 확인해주세요.")
    private String name;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "^[0-9]*$",
            message = "아이디는 숫자만 포함해야 합니다.")
    private String phoneNumber;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z\\d]*$",
            message = "아이디는 영어 대소문자와 숫자만 포함해야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,16}$",
            message = "비밀번호는 8~16자리이며 영문 대문자, 소문자, 숫자, 특수문자를 각각 최소 하나씩 포함해야 합니다.")
    private String password;

}
