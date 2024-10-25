package com.app.community.domain.model.member;

import lombok.Getter;

@Getter
public class Member {
    private final Long id;
    private MemberProfile profile;
    private MemberSocial socialInfo;
    private MemberGrade grade;
    private MemberStatus status;

    public Member(
            Long id,
            MemberProfile profile,
            MemberSocial socialInfo,
            MemberGrade grade,
            MemberStatus status
    ) {
        this.id = id;
        this.profile = profile;
        this.socialInfo = socialInfo;
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
        this.grade = grade.applyPointsAndUpdateTier(points);
    }
}
