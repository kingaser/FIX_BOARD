package com.sparta.board.entity;

import com.sparta.board.dto.request.BoardRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Board(BoardRequest boardRequest, User user) {
        super();
        this.user = user;
        title = boardRequest.getTitle();
        author = boardRequest.getAuthor();
        contents = boardRequest.getContents();
    }

    public void update(BoardRequest boardRequest) {
        title = boardRequest.getTitle();
        author = boardRequest.getAuthor();
        contents = boardRequest.getContents();
    }

    public static Board of(BoardRequest boardRequest, User user) {
        return Board.builder()
                .boardRequest(boardRequest)
                .user(user)
                .build();
    }

}
