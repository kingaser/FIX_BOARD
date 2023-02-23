package com.sparta.board.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.board.entity.Board;
import com.sparta.board.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private List<CommentResponse> commentResponseList = new ArrayList<>();
    private Long likeCount;

    @Builder
    private BoardResponse(Board board, List<CommentResponse> commentResponseList, Long likeCount) {
        id = board.getId();
        title = board.getTitle();
        contents = board.getContents();
        createAt = board.getCreateAt();
        modifiedAt = board.getModifiedAt();
        this.likeCount = likeCount;
        this.commentResponseList = commentResponseList;
    }

    public static BoardResponse from(Board board, List<CommentResponse> commentResponseList, Long likeCount) {
        return BoardResponse.builder()
                .board(board)
                .commentResponseList(commentResponseList)
                .likeCount(likeCount)
                .build();
    }

    public static BoardResponse from(Board board) {
        return BoardResponse.builder()
                .board(board)
                .commentResponseList(new ArrayList<>())
                .likeCount(0L)
                .build();
    }

}
