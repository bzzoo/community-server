package com.app.community.domain.agg.member;

public record MemberGrade(
        int value,
        MemberTier tier
) {
    private static final int TIER_INTERVAL = 250;
    private static final int BASE_SCORE = 1000;

    public static MemberGrade init() {
        return new MemberGrade(BASE_SCORE, MemberTier.BRONZE);
    }

    public MemberGrade applyPointsAndUpdateTier(int pointsToAdd) {
        int newValue = this.value + pointsToAdd;
        MemberTier newTier = switch ((newValue - BASE_SCORE) / TIER_INTERVAL) {
            case 0 -> MemberTier.BRONZE;
            case 1 -> MemberTier.SILVER;
            case 2 -> MemberTier.GOLD;
            case 3 -> MemberTier.PLATINUM;
            case 4 -> MemberTier.DIAMOND;
            default -> newValue >= BASE_SCORE + 5 * TIER_INTERVAL ? MemberTier.RUBY : MemberTier.BRONZE;
        };
        return new MemberGrade(newValue, newTier);
    }
}

