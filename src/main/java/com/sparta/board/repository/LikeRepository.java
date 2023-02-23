package com.sparta.board.repository;

import com.sparta.board.entity.Board;
import com.sparta.board.entity.Comment;
import com.sparta.board.entity.Like;
import com.sparta.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByBoardAndUser(Board board, User user);
    Optional<Like> findByCommentAndUser(Comment comment, User user);

    void deleteByCommentId(Long commentId);
    void deleteByBoardId(Long boardId);

    Long countCommentLikeByCommentId(Long commentId);

    Long countBoardLikeByBoardId(Long boardId);
}
