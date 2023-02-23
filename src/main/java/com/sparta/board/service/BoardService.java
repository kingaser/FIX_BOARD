package com.sparta.board.service;

import com.sparta.board.dto.request.BoardRequest;
import com.sparta.board.dto.response.BoardResponse;
import com.sparta.board.dto.response.CommentResponse;
import com.sparta.board.dto.response.MessageResponse;
import com.sparta.board.entity.Board;
import com.sparta.board.entity.Comment;
import com.sparta.board.repository.BoardRepository;
import com.sparta.board.repository.CommentRepository;
import com.sparta.board.repository.LikeRepository;
import com.sparta.board.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Transactional(readOnly = true)
    public List<BoardResponse> getBoards() {
        List<Board> boards = boardRepository.findAllByOrderByModifiedAtDesc();
        List<BoardResponse> boardResponseList = new ArrayList<>();

        for (Board board : boards) {
            List<Comment> commentList = commentRepository.findAllByBoard(board);
            List<CommentResponse> commentResponseList = new ArrayList<>();
            for (Comment comment : commentList) {
                Long commentLikeCount = likeRepository.countCommentLikeByCommentId(comment.getId());
                commentResponseList.add(CommentResponse.from(comment, commentLikeCount));
            }
            boardResponseList.add(BoardResponse.from(board, commentResponseList, likeRepository.countBoardLikeByBoardId(board.getId())));
        }
        return boardResponseList;
    }

    public BoardResponse createBoard(BoardRequest boardRequest, UserDetailsImpl userDetails) {

        Board board = boardRepository.save(Board.of(boardRequest, userDetails.getUser()));
        return BoardResponse.from(board);
    }

    @Transactional(readOnly = true)
    public BoardResponse getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다.")
        );
        return BoardResponse.from(board);
    }

    public BoardResponse updateBoard(Long id, BoardRequest boardRequest, UserDetailsImpl userDetails) {

        Board board = boardRepository.findByIdAndUserId(id, userDetails.getUser().getId()).orElseThrow(
                () -> new IllegalArgumentException("자신이 작성한 게시글만 수정/삭제가 가능합니다.")
        );

        board.update(boardRequest);
        return BoardResponse.from(board);
    }


    public MessageResponse deleteBoard(Long id, UserDetailsImpl userDetails) {

        Board board = boardRepository.findByIdAndUserId(id, userDetails.getUser().getId()).orElseThrow(
                () -> new NullPointerException("게시글이 없습니다.")
        );
        List<Comment> commentList = commentRepository.findAllByBoard(board);
        for (Comment comment : commentList) {
            likeRepository.deleteByCommentId(comment.getId());
        }
        commentRepository.deleteAllByBoardId(board.getId());
        likeRepository.deleteByBoardId(board.getId());
        boardRepository.deleteById(id);
        return new MessageResponse(HttpStatus.OK.value(), "게시글/댓글 삭제 완료");
    }

//    public User isToken(HttpServletRequest request) {
//        String token = jwtUtil.resolveToken(request);
//        Claims claims;
//        if (token == null && !jwtUtil.validateToken(token)) {
//            throw new IllegalArgumentException("Token Error");
//        }
//        claims = jwtUtil.getUserInfoFromToken(token);
//
//        return userRepository.findByUsername(claims.getSubject()).orElseThrow(
//                () -> new NullPointerException("사용자가 존재하지 않습니다.")
//        );
//    }
}
