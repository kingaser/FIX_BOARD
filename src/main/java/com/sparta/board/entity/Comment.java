package com.sparta.board.entity;

import com.sparta.board.dto.request.CommentRequest;
import com.sparta.board.dto.response.CommentResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    private Comment(CommentRequest commentRequest, Board board, User user) {
        comments = commentRequest.getComments();
        this.board = board;
        this.user = user;
    }

    public void update(CommentRequest commentRequest) {
        comments = commentRequest.getComments();
    }

    public static Comment of(CommentRequest commentRequest, User user, Board board) {
        return Comment.builder()
                .commentRequest(commentRequest)
                .user(user)
                .board(board)
                .build();
    }
}
