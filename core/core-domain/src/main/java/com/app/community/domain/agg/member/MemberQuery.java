package com.app.community.domain.agg.member;

public class MemberQuery {

    public record MemberInfo(
            Long memberId,
            MemberProfile profile,
            MemberSocial social,
            MemberStatus status,
            MemberGrade grade
    ) {

        public static MemberInfo of(Member member) {
            return new MemberInfo(
                    member.getId(),
                    member.getProfile(),
                    member.getSocialInfo(),
                    member.getStatus(),
                    member.getGrade()
            );
        }
    }
}
