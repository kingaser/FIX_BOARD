package com.sparta.board.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.board.entity.Comment;
import lombok.Builder;
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
    private Long commentLikeCount;

    @Builder
    private CommentResponse(Comment comment, Long commentLikeCount) {
        id = comment.getId();
        username = comment.getUser().getUsername();
        comments = comment.getComments();
        createdAt = comment.getCreateAt();
        modifiedAt = comment.getModifiedAt();
        this.commentLikeCount = commentLikeCount;
    }

    public static CommentResponse from(Comment comment, Long commentLikeCount) {
        return CommentResponse.builder()
                .comment(comment)
                .commentLikeCount(commentLikeCount)
                .build();
    }

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .comment(comment)
                .commentLikeCount(0L)
                .build();
    }
}
