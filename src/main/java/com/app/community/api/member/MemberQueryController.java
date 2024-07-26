package com.app.community.api.member;

import com.app.community.domain.member.MemberServiceForQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.community.domain.member.MemberSummary.*;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberQueryController {

    private final MemberServiceForQuery memberServiceForQuery;

    @GetMapping("/me")
    public ResponseEntity<MemberInfo> me(
            @AuthenticationPrincipal Long memberId
    ) {
        MemberInfo memberInfo = memberServiceForQuery.getMemberInfo(memberId);
        return ResponseEntity.ok().body(memberInfo);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberInfo> getMyProfile(
            @PathVariable(name = "memberId") Long memberId
    ) {
        MemberInfo memberInfo = memberServiceForQuery.getMemberInfo(memberId);
        return ResponseEntity.ok().body(memberInfo);
    }
}
