package lotto.domain;

import java.util.Locale;
import java.util.Comparator;

enum Prize {

    MATCH_SIX(6, false, 2_000_000_000),
    MATCH_FIVE_WITH_BONUS(5, true, 30_000_000),
    MATCH_FIVE_WITHOUT_BONUS(5, false, 1_500_000),
    MATCH_FOUR(4, false, 50_000),
    MATCH_THREE(3, false, 5_000),
    MATCH_NONE(0, false, 0);

    public final int money;
    private final int matches;
    private final boolean isBonusMatch;

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
        return StringTemplate.prize(matches, this == MATCH_FIVE_WITH_BONUS, money);
    }

    record MatchRangeRule(int minInclusive, int maxInclusive) {
        static final MatchRangeRule DEFAULT = new MatchRangeRule(0, 6);

        void validate(int matches) {
            if (matches < minInclusive || maxInclusive < matches) {
                throw LottoProblem.NUMBER_OUT_OF_RANGE.exception();
            }
        }
    }

    static Comparator<Prize> comparator() {
        return (p0, p1) -> Integer.compare(p0.money, p1.money);
    }
}
