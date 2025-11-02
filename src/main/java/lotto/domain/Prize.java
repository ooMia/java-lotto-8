package lotto.domain;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

enum Prize {

    MATCH_SIX(6, false, 2_000_000_000),
    MATCH_FIVE_WITH_BONUS(5, true, 30_000_000),
    MATCH_FIVE_WITHOUT_BONUS(5, false, 1_500_000),
    MATCH_FOUR(4, false, 50_000),
    MATCH_THREE(3, false, 5_000),
    MATCH_NONE(0, false, 0);

    private static final Map<Integer, Prize> cache = new HashMap<>();

    static {
        for (var prize : Prize.values()) {
            var key = keyHashCode(prize.matches, prize.isBonusMatch);
            cache.put(key, prize);
        }
    }

    public final int money;
    final int matches;
    final boolean isBonusMatch;

    Prize(int matches, boolean isBonusMatch, int money) {
        this.matches = matches;
        this.isBonusMatch = isBonusMatch;
        this.money = money;
    }

    static Prize ofResult(int matches, boolean isBonusMatch) {
        MatchRangeRule.DEFAULT.validate(matches);
        var key = keyHashCode(matches, isBonusMatch);
        return cache.getOrDefault(key, MATCH_NONE);
    }

    static int keyHashCode(int matches, boolean isBonusMatch) {
        if (matches == Prize.MATCH_FIVE_WITH_BONUS.matches && isBonusMatch) {
            return Prize.values().length + matches;
        }
        return matches;
    }

    static Comparator<Prize> comparator() {
        return Comparator.comparingInt(p0 -> p0.money);
    }

    @Override
    public String toString() {
        return Presentation.instance.prize(matches, this == MATCH_FIVE_WITH_BONUS, money);
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
