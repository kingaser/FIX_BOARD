package com.sparta.board.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.board.entity.Board;
import com.sparta.board.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardResponse {

    private Long id;
    private String title;
    private String contents;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy:mm:dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy:mm:dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;
    private List<CommentResponse> commentResponseList;

    public BoardResponse(Board board) {
        id = board.getId();
        title = board.getTitle();
        contents = board.getContents();
        createAt = board.getCreateAt();
        modifiedAt = board.getModifiedAt();
    }

    public BoardResponse(Board board, List<CommentResponse> commentResponseList) {
        id = board.getId();
        title = board.getTitle();
        contents = board.getContents();
        createAt = board.getCreateAt();
        modifiedAt = board.getModifiedAt();
        this.commentResponseList = commentResponseList;
    }
}
