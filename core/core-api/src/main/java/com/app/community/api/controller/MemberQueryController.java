package com.app.community.api.controller;

import com.app.community.domain.model.member.LoginMember;
import com.app.community.domain.query.MemberQuery.MemberInfo;
import com.app.community.domain.query.MemberQueryService;
import com.app.community.domain.model.point.PointHistory;
import com.app.community.api.support.response.ApiResponse;
import com.app.community.api.support.response.CursorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberQueryController {

    private final MemberQueryService memberQueryService;

    @GetMapping("/me")
    ApiResponse<MemberInfo> me(
            @AuthenticationPrincipal LoginMember member
    ) {
        MemberInfo memberInfo = memberQueryService.getMemberInfo(member.memberId());
        return ApiResponse.success(memberInfo);
    }

    @GetMapping("/{memberId}")
    ApiResponse<MemberInfo> getMyProfile(
            @AuthenticationPrincipal LoginMember member
    ) {
        MemberInfo memberInfo = memberQueryService.getMemberInfo(member.memberId());
        return ApiResponse.success(memberInfo);
    }

    @GetMapping("/point-history")
    ApiResponse<CursorResult<PointHistory>> getPointHistory(
            @AuthenticationPrincipal LoginMember member,
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor
    ) {
        var pointHistories= memberQueryService.getPointHistory(member.memberId(), size + 1, cursor);
        var pointHistory = CursorResult.of(pointHistories, size, PointHistory::getId);
        return ApiResponse.success(pointHistory);
    }
}
