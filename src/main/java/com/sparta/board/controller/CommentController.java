package com.sparta.board.controller;


import com.sparta.board.dto.request.CommentRequest;
import com.sparta.board.dto.response.CommentResponse;
import com.sparta.board.dto.response.MessageResponse;
import com.sparta.board.security.UserDetailsImpl;
import com.sparta.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/{id}")
    public CommentResponse createComments(@PathVariable Long id, @RequestBody CommentRequest commentRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComments(id, commentRequest, userDetails);
    }

    @PutMapping("/comment/update/{id}")
    public CommentResponse updateComment(@PathVariable Long id, @RequestBody CommentRequest commentRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(id, commentRequest, userDetails);
    }

    @DeleteMapping("/comment/delete/{id}")
    public MessageResponse deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(id, userDetails);
    }
}
