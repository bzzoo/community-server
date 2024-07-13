package com.app.community.domain.member;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberAppender {

    private final MemberRepository memberRepository;

    public Member register(
            Member.SocialInfo socialInfo,
            @Nullable String email,
            @Nullable String profileImagePath
    ) {
        Member newMember = Member.registerBySocial(socialInfo, email, profileImagePath);
        return memberRepository.save(newMember);
    }

    public void onboard(Member member, String nickname, Member.Position memberPosition) {
        member.onboard(nickname, memberPosition);
        memberRepository.save(member);
    }

    public void updateSettings(Member member, Boolean chatRefusal, Integer chatPeePoint) {
        member.updateSettings(new Member.Settings(chatPeePoint, chatRefusal));
        memberRepository.save(member);
    }
}
