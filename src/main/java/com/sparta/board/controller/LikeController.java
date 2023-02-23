package com.sparta.board.controller;

import com.sparta.board.dto.response.MessageResponse;
import com.sparta.board.security.UserDetailsImpl;
import com.sparta.board.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/board/{id}")
    public MessageResponse likeBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.likeBoard(id, userDetails);
    }

    @PostMapping("/comment/{id}")
    public MessageResponse likeComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.likeComment(id, userDetails);
    }
}
