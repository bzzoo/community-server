package com.app.community.controller;

import com.app.community.domain.agg.member.MemberQuery.MemberInfo;
import com.app.community.domain.agg.member.MemberReadService;
import com.app.community.domain.agg.point.PointHistory;
import com.app.community.support.response.CursorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberQueryController {

    private final MemberReadService memberReadService;

    @GetMapping("/me")
    public ResponseEntity<MemberInfo> me(
            @AuthenticationPrincipal Long memberId
    ) {
        MemberInfo memberInfo = memberReadService.getMemberInfo(memberId);
        return ResponseEntity.ok().body(memberInfo);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberInfo> getMyProfile(
            @PathVariable(name = "memberId") Long memberId
    ) {
        MemberInfo memberInfo = memberReadService.getMemberInfo(memberId);
        return ResponseEntity.ok().body(memberInfo);
    }

    @GetMapping("/point-history")
    public ResponseEntity<CursorResult<PointHistory>> getPointHistory(
            @AuthenticationPrincipal Long memberId,
            @RequestParam(name = "s", required = false, defaultValue = "20") int size,
            @RequestParam(name = "c", required = false, defaultValue = "-1") Long cursor
    ) {
        List<PointHistory> pointHistories= memberReadService.getPointHistory(memberId, size);
        return ResponseEntity.ok().body(CursorResult.of(pointHistories, size, PointHistory::getId));
    }
}
