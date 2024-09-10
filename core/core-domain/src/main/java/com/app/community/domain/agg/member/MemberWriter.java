package com.app.community.domain.agg.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberWriter {

    private final MemberRepository memberRepository;

    public Member create(MemberSocial social, MemberProfile profile) {
        Member member = Member.create(social, profile);
        return memberRepository.save(member);
    }

    public Member update(Member member){
        return memberRepository.save(member);
    }

    public Member updateProfile(Member member, MemberProfile profile) {
        member.update(profile);
        return memberRepository.save(member);
    }
}
