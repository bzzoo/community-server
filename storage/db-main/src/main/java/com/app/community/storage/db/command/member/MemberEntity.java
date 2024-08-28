package com.app.community.storage.db.command.member;

import com.app.community.domain.agg.member.*;
import com.app.community.storage.db.command.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Setter
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
    private int chatPeePoint;
    private boolean chatRefusal;
    private MemberPosition position;
    private int pointValue;
    private MemberTier tier;
    private String socialId;
    private MemberSocialType memberSocialType;
    private MemberStatus status;

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
