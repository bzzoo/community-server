package com.app.community.controller;

import com.app.community.domain.agg.member.LoginMember;
import com.app.community.domain.agg.member.MemberQuery.MemberInfo;
import com.app.community.domain.agg.member.MemberReadService;
import com.app.community.domain.agg.point.PointHistory;
import com.app.community.support.response.ApiResponse;
import com.app.community.support.response.CursorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberQueryController {

    private final MemberReadService memberReadService;

    @GetMapping("/me")
    ApiResponse<MemberInfo> me(
            @AuthenticationPrincipal LoginMember member
    ) {
        MemberInfo memberInfo = memberReadService.getMemberInfo(member.memberId());
        return ApiResponse.success(memberInfo);
    }

    @GetMapping("/{memberId}")
    ApiResponse<MemberInfo> getMyProfile(
            @AuthenticationPrincipal LoginMember member
    ) {
        MemberInfo memberInfo = memberReadService.getMemberInfo(member.memberId());
        return ApiResponse.success(memberInfo);
    }

    @GetMapping("/point-history")
    ApiResponse<CursorResult<PointHistory>> getPointHistory(
            @AuthenticationPrincipal LoginMember member,
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor
    ) {
        var pointHistories= memberReadService.getPointHistory(member.memberId(), size, cursor);
        var pointHistory = CursorResult.of(pointHistories, size, PointHistory::getId);
        return ApiResponse.success(pointHistory);
    }
}
