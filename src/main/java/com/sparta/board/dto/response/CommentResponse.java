package com.sparta.board.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.board.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponse {

    private Long id;
    private String username;
    private String comments;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;


    public CommentResponse(Comment comment) {
        id = comment.getId();
        username = comment.getUser().getUsername();
        comments = comment.getComments();
        createdAt = comment.getCreateAt();
        modifiedAt = comment.getModifiedAt();
    }
}
