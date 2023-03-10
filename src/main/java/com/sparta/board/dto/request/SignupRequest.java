package com.sparta.board.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class SignupRequest {

    @NotBlank(message = "ID는 필수 입력값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{4,10}", message = "ID는 영어 소문자와 숫자로 이루어진, 4자~10자의 ID여야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z0-9\\d~!@#$%^&*()+|=]{8,15}$",

            message = "비밀번호는 영문자와 숫자, 특수문자 이루어진, 8자~15자의 비밀번호여야 합니다.")
    private String password;
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}
