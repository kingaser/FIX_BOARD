package com.sparta.board.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {
    private String username;
    private String password;
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}
