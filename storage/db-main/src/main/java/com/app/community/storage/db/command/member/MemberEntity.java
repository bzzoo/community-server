package com.app.community.storage.db.command.member;

import com.app.community.domain.model.member.*;
import com.app.community.storage.db.command.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
@Entity
public class MemberEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nickname;
    private String profileImagePath;
    private String socialId;
    private int chatPeePoint;
    private boolean chatRefusal;
    private int pointValue;

    @Enumerated(EnumType.STRING)
    private MemberPosition position;
    @Enumerated(EnumType.STRING)
    private MemberSocialType memberSocialType;
    @Enumerated(EnumType.STRING)
    private MemberTier tier;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    public static MemberEntity fromDomain(Member member) {
        return MemberEntity.builder()
                .id(member.getId())
                .email(member.getProfile().email())
                .nickname(member.getProfile().nickname().value())
                .profileImagePath(member.getProfile().profileImagePath())
                .chatPeePoint(member.getProfile().memberSetting().chatPeePoint())
                .chatRefusal(member.getProfile().memberSetting().chatRefusal())
                .position(member.getProfile().position())
                .pointValue(member.getGrade().value())
                .tier(member.getGrade().tier())
                .socialId(member.getSocialInfo().socialId())
                .memberSocialType(member.getSocialInfo().memberSocialType())
                .status(member.getStatus())
                .build();
    }

    public Member toDomain() {
        MemberNickname memberNickname = new MemberNickname(nickname);
        MemberSetting memberSetting = new MemberSetting(chatPeePoint, chatRefusal);
        MemberProfile memberProfile = new MemberProfile(email, profileImagePath, memberNickname, memberSetting, position);
        MemberSocial memberSocial = new MemberSocial(socialId, memberSocialType);
        MemberGrade memberGrade = new MemberGrade(pointValue, tier);
        return new Member(
                id,
                memberProfile,
                memberSocial,
                memberGrade,
                status
        );
    }

}
