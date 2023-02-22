package com.sparta.board.dto.response;

import lombok.Getter;

@Getter
public class MessageResponse {

    private int status;
    private String message;

    public MessageResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
