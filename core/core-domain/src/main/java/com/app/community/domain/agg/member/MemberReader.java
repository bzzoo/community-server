package com.app.community.domain.agg.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MemberReader {

    private final MemberRepository memberRepository;

    public Member getById(Long memberId){
        return findById(memberId).orElseThrow();
    }

    public Optional<Member> findById(Long memberId){
        return memberRepository.findById(memberId);
    }

    public Optional<Member> findBySocial(MemberSocial socialInfo){
        return memberRepository.findBySocialInfo(socialInfo);
    }
}
