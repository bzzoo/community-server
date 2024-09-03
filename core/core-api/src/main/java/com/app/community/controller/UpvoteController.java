package com.app.community.controller;

import com.app.community.domain.agg.upvote.UpvoteService;
import com.app.community.controller.request.UpvoteRequests.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/api/upvotes")
@RestController
public class UpvoteController {

    private final UpvoteService upvoteService;

    @PostMapping("/")
    public ResponseEntity<Void> upvote(
            @AuthenticationPrincipal Long memberId,
            @RequestBody UpvotedRequest request
    ) {
        upvoteService.upvote(memberId, request.opponentId(), request.toTarget());
        return ResponseEntity.ok().build();
    }
}
