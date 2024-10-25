package com.app.community.api.controller;

import com.app.community.api.controller.request.UpvoteRequests;
import com.app.community.domain.model.member.LoginMember;
import com.app.community.domain.model.upvote.UpvoteService;
import com.app.community.api.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
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

    @PostMapping()
    ApiResponse<Void> upvote(
            @AuthenticationPrincipal LoginMember member,
            @RequestBody UpvoteRequests.UpvotedRequest request
    ) {
        upvoteService.upvote(member.memberId(), request.opponentId(), request.toTarget());
        return ApiResponse.success();
    }
}
