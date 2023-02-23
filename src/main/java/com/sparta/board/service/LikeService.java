package com.sparta.board.service;

import com.sparta.board.dto.response.MessageResponse;
import com.sparta.board.entity.Board;
import com.sparta.board.entity.Comment;
import com.sparta.board.entity.Like;
import com.sparta.board.entity.User;
import com.sparta.board.repository.BoardRepository;
import com.sparta.board.repository.CommentRepository;
import com.sparta.board.repository.LikeRepository;
import com.sparta.board.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;

    public MessageResponse likeBoard(Long id, UserDetailsImpl userDetails) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글/댓글이 없습니다.")
        );

        Optional<Like> found = likeRepository.findByBoardAndUser(board, userDetails.getUser());
        if (found.isEmpty()) {
            Like like = Like.of(board, userDetails.getUser());
            likeRepository.save(like);
            return new MessageResponse(HttpStatus.OK.value(), "좋아요 추가");
        } else {
            likeRepository.delete(found.get());
            likeRepository.flush();
            return new MessageResponse(HttpStatus.OK.value(), "좋아요 삭제");
        }

    }

    public MessageResponse likeComment(Long id, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글/댓글이 없습니다.")
        );
        Optional<Like> found = likeRepository.findByCommentAndUser(comment, userDetails.getUser());
        if (found.isEmpty()) {
            Like like = Like.of(comment, userDetails.getUser());
            likeRepository.save(like);
            return new MessageResponse(HttpStatus.OK.value(), "좋아요 성공");
        } else {
            likeRepository.delete(found.get());
            likeRepository.flush();
            return new MessageResponse(HttpStatus.OK.value(), "좋아요 삭제");
        }
    }
}
