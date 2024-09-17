package com.app.community.controller;

import com.app.community.domain.agg.member.LoginMember;
import com.app.community.domain.agg.member.MemberService;
import com.app.community.controller.request.MemberRequests.*;
import com.app.community.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    ApiResponse<Void> updateProfile(
            @AuthenticationPrincipal LoginMember member,
            @RequestBody UpdateProfileRequest request
    ) {
        memberService.updateProfile(member, request.toProfile());
        return ApiResponse.success();
    }
}
