package com.sparta.board.controller;

import com.sparta.board.dto.request.BoardRequest;
import com.sparta.board.dto.response.BoardResponse;
import com.sparta.board.dto.response.MessageResponse;
import com.sparta.board.security.UserDetailsImpl;
import com.sparta.board.service.BoardService;
import com.sparta.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public List<BoardResponse> getBoards() {
        return boardService.getBoards();
    }

    @PostMapping("/board/post")
    public BoardResponse createBoard(@RequestBody BoardRequest boardRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.createBoard(boardRequest, userDetails);
    }

    @GetMapping("/boards/{id}")
    public BoardResponse getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PutMapping("/board/update/{id}")
    public BoardResponse updateBoard(@PathVariable Long id, @RequestBody BoardRequest boardRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.updateBoard(id, boardRequest, userDetails);
    }

    @DeleteMapping("/board/delete/{id}")
    public MessageResponse deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.deleteBoard(id, userDetails);
    }
}
