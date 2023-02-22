package com.sparta.board.service;

import com.sparta.board.dto.request.CommentRequest;
import com.sparta.board.dto.response.CommentResponse;
import com.sparta.board.dto.response.MessageResponse;
import com.sparta.board.entity.Board;
import com.sparta.board.entity.Comment;
import com.sparta.board.repository.BoardRepository;
import com.sparta.board.repository.CommentRepository;
import com.sparta.board.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public CommentResponse createComments(Long id, CommentRequest commentRequest, UserDetailsImpl userDetails) {

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글/댓글이 존재하지 않습니다.")
        );

        Comment comment = commentRepository.save(Comment.builder()
                .commentRequest(commentRequest)
                .board(board)
                .user(userDetails.getUser())
                .build());
        return new CommentResponse(comment);
    }

    public CommentResponse updateComment(Long id, CommentRequest commentRequest, UserDetailsImpl userDetails) {

        Comment comment = commentRepository.findByIdAndUserId(id, userDetails.getUser().getId()).orElseThrow(
                () -> new NullPointerException("게시글/댓글이 존재하지 않습니다.")
        );

        comment.update(commentRequest);
        commentRepository.saveAndFlush(comment);
        return new CommentResponse(comment);
    }

    public void deleteComment(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글/댓글이 존재하지 않습니다.")
        );
        commentRepository.deleteAllByBoardId(id);
    }

    public MessageResponse deleteComment(Long id, UserDetailsImpl userDetails) {

        Comment comment = commentRepository.findByIdAndUserId(id, userDetails.getUser().getId()).orElseThrow(
                () -> new NullPointerException("게시글/댓글이 존재하지 않습니다.")
        );

        commentRepository.deleteById(id);

        return new MessageResponse(HttpStatus.OK.value(), "게시글/댓글 삭제 완료");
    }
}
