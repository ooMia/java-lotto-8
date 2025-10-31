package lotto;

import java.util.Locale;

public enum Prize {

    MATCH_SIX(6, false, 2_000_000_000),
    MATCH_FIVE_WITH_BONUS(5, true, 30_000_000),
    MATCH_FIVE_WITHOUT_BONUS(5, false, 1_500_000),
    MATCH_FOUR(4, false, 50_000),
    MATCH_THREE(3, false, 5_000),
    MATCH_NONE(0, false, 0);

    private int matches;
    private boolean isBonusMatch;
    private int money;

    Prize(int matches, boolean isBonusMatch, int money) {
        this.matches = matches;
        this.isBonusMatch = isBonusMatch;
        this.money = money;
    }

    static Prize of(int matches, boolean isBonusMatch) {
        MatchRangeRule.DEFAULT.validate(matches);
        for (var prize : Prize.values()) {
            if (prize.equals(matches, isBonusMatch)) {
                return prize;
            }
        }
        return MATCH_NONE;
    }

    boolean equals(int matches, boolean isBonusMatch) {
        if (this.isBonusMatch) {
            return isBonusMatch && this.matches == matches;
        }
        return this.matches == matches;
    }

    @Override
    public String toString() {
        return String.format(Locale.KOREAN, "%d개 일치%s (%,d원)", matches, bonus(), money);
    }

    private String bonus() {
        if (this == MATCH_FIVE_WITH_BONUS) {
            return ", 보너스 볼 일치";
        }
        return "";
    }

    record MatchRangeRule(int minInclusive, int maxInclusive) {
        static final MatchRangeRule DEFAULT = new MatchRangeRule(0, 6);

        void validate(int matches) {
            if (matches < minInclusive || maxInclusive < matches) {
                throw LottoProblem.NUMBER_OUT_OF_RANGE.exception();
            }
        }
    }
}
