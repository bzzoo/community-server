package com.app.community.domain.model.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberRegister {

    private final MemberWriter memberWriter;
    private final MemberReader memberReader;

    public Member register(MemberSocial socialInfo, String email, String profileImagePath) {
        return memberReader.findBySocial(socialInfo)
                .orElseGet(() -> memberWriter.create(
                        socialInfo,
                        MemberProfile.init(email, profileImagePath)
                ));
    }
}
