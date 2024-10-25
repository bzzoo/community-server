package com.app.community.domain.model.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberReader memberReader;
    private final MemberWriter memberWriter;

    public Member updateProfile(LoginMember loginMember, MemberProfile profile) {
        Member member = memberReader.getById(loginMember.memberId());
        return memberWriter.updateProfile(member, profile);

    }
}
