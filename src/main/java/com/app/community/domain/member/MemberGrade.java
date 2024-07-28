package com.app.community.domain.member;

public class MemberGrade {
    public record Grade(
            int value,
            Member.Tier grade
    ) {
        private static final int TIER_INTERVAL = 250;
        private static final int BASE_SCORE = 1000;

        public static Grade init(){
            return new Grade(BASE_SCORE, Member.Tier.BRONZE);
        }

        public Grade addValue(int value) {
            return new Grade(this.value + value, this.grade).calculateTier();
        }

        public Grade subtractScore(int value){
            return new Grade(this.value - value, this.grade).calculateTier();
        }

        public Grade calculateTier() {
            Member.Tier newTier = switch ((value - BASE_SCORE) / TIER_INTERVAL) {
                case 0 -> Member.Tier.BRONZE;
                case 1 -> Member.Tier.SILVER;
                case 2 -> Member.Tier.GOLD;
                case 3 -> Member.Tier.PLATINUM;
                case 4 -> Member.Tier.DIAMOND;
                default -> value >= BASE_SCORE + 5 * TIER_INTERVAL ? Member.Tier.RUBY : Member.Tier.BRONZE;
            };
            return new Grade(value, newTier);
        }

        public static Grade of(int value) {
            return new Grade(value, Member.Tier.BRONZE).calculateTier();
        }
    }
}
