package com.app.community.domain.agg.member;

import lombok.Getter;

@Getter
public class Member {
    private Long id;
    private MemberProfile profile;
    private MemberSocial social;
    private MemberGrade grade;
    private MemberStatus status;

    public Member(
            Long id,
            MemberProfile profile,
            MemberSocial social,
            MemberGrade grade,
            MemberStatus status
    ) {
        this.id = id;
        this.profile = profile;
        this.social = social;
        this.grade = grade;
        this.status = status;
    }

    public static Member create(
            MemberSocial social,
            MemberProfile profile
    ) {
        return new Member(null, profile, social, MemberGrade.init(), MemberStatus.ACTIVE);
    }

    public void update(MemberProfile profile) {
        this.profile = profile;
    }

    public void updatePoints(int points) {
        this.grade = new MemberGrade(
                grade.value() + points,
                grade.tier()
        ).calculateTier();
    }
}
