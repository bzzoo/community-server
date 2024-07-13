package com.app.community.storage.member;

import com.app.community.domain.member.Member;
import com.app.community.domain.member.SocialType;
import com.app.community.storage.AbstractEntity;
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
    private int money;
    private String socialId;
    private SocialType socialType;
    private int chatPeePoint;
    private boolean chatRefusal;
    private int pointValue;
    private Member.Position position;
    private Member.Status status;
    private Member.Tier tier;


    public static MemberEntity fromDomain(Member member) {
        return MemberEntity.builder()
                .id(member.getId())
                .socialId(member.getSocialInfo().socialId())
                .socialType(member.getSocialInfo().socialType())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .profileImagePath(member.getEmail())
                .money(member.getMoney().value())
                .chatPeePoint(member.getSettings().chatPeePoint())
                .chatRefusal(member.getSettings().chatRefusal())
                .position(member.getPosition())
                .status(member.getStatus())
                .pointValue(member.getGrade().value())
                .tier(member.getGrade().grade())
                .build();
    }

    public Member toDomain() {
        return Member.builder()
                .id(id)
                .email(email)
                .nickname(nickname)
                .profileImagePath(email)
                .socialInfo(new Member.SocialInfo(socialId, socialType))
                .money(new Member.Money(money))
                .settings(new Member.Settings(chatPeePoint, chatRefusal))
                .position(position)
                .status(status)
                .grade(new Member.Grade(pointValue, tier))
                .build();
    }
}
