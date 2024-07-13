package com.app.community.domain.member;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MemberReader {

    private final MemberRepository memberRepository;

    public Member getById(@NotNull Long memberId){
        return findById(memberId).orElseThrow();
    }

    public Optional<Member> findById(@NotNull Long memberId){
        return memberRepository.findById(memberId);
    }

    public Optional<Member> findBySocial(Member.SocialInfo socialInfo){
        return memberRepository.findBySocialInfo(socialInfo);
    }
}
