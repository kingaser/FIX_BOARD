package com.sparta.board.repository;

import com.sparta.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndUserId(Long id, Long userid);

    List<Comment> findAllByBoardId(Long boardId);

    Optional<Comment> deleteAllByBoardId(Long boardId);
}
