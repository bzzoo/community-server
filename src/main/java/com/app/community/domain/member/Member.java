package com.app.community.domain.member;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class Member {

    private @Nullable Long id;
    private @Nullable String email;
    private @Nullable String profileImagePath;  //TODO @Nullable 모델 분리 하기
    private @NotNull String nickname;
    private @NotNull Money money;
    private @NotNull SocialInfo socialInfo;
    private @NotNull Settings settings;
    private @NotNull Position position;
    private @NotNull Status status;
    private @NotNull Grade grade;

    public record SocialInfo(
            String socialId,
            SocialType socialType
    ) {
    }

    public record Grade(
            int value,
            Tier grade
    ) {
        public static Grade init(){
            return new Grade(1000, Tier.BRONZE);
        }
    }

    public record Money(
            int value
    ) {
        public static Money init() {
            return new Money(100);
        }
    }

    public record Settings(
            int chatPeePoint,
            boolean chatRefusal
    ) {
        public static Settings init(){
            return new Settings(3, false);
        }
    }

    public enum Status {
        ACTIVE, INACTIVE, SUSPENSE
    }

    public enum Tier {
        BRONZE, SILVER, GOLD, PLATINUM, DIAMOND, RUBY
    }

    public enum Position {
        NONE, DEVELOPER
    }

    @Builder
    private Member(
            @Nullable Long id,
            @Nullable String email,
            @Nullable String profileImagePath,
            @NotNull String nickname,
            @NotNull SocialInfo socialInfo,
            @NotNull Money money,
            @NotNull Settings settings,
            @NotNull Grade grade,
            @NotNull Position position,
            @NotNull Status status
    ) {
        this.id = id;
        this.email = email;
        this.profileImagePath = profileImagePath;
        this.nickname = nickname;
        this.socialInfo = socialInfo;
        this.money = money;
        this.settings = settings;
        this.position = position;
        this.status = status;
        this.grade = grade;
    }

    public static Member registerBySocial(
            SocialInfo socialInfo,
            @Nullable String email,
            @Nullable String profileImagePath
    ) {
        return Member.builder()
                .socialInfo(socialInfo)
                .email(email)
                .profileImagePath(profileImagePath)
                .nickname("임시")
                .socialInfo(socialInfo)
                .money(Money.init())
                .settings(Settings.init())
                .position(Position.NONE)
                .status(Status.SUSPENSE)
                .grade(Grade.init())
                .build();
    }

    public void onboard(String nickname, Position memberPosition) {
        this.nickname = nickname;
        this.position = memberPosition;
    }

    public void updateSettings(Settings settings) {
        this.settings = settings;
    }
}