package com.app.community.domain.member;

public class MemberSummary {

    public record MemberInfo(
            Long memberId,
            String email,
            String nickname,
            String profileImagePath,
            Member.Money money,
            Member.SocialInfo socialInfo,
            Member.Settings settings,
            Member.Position position,
            Member.Status status,
            MemberGrade.Grade grade
    ) {
        public static MemberInfo of(Member member) {
            return new MemberInfo(
                    member.getId(),
                    member.getEmail(),
                    member.getNickname(),
                    member.getProfileImagePath(),
                    member.getMoney(),
                    member.getSocialInfo(),
                    member.getSettings(),
                    member.getPosition(),
                    member.getStatus(),
                    member.getGrade()
            );
        }
    }
}
