package lotto;

import java.util.Locale;

// TODO 뭔가 Vendor한테 종속시켜야하나 싶기도 하고
// 근데 딱히 모두한테 공개된 정보라고 생각해서, 필드도 다 공개해버리고 싶긴함 << 일단 상금은 공개
public enum Prize {

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


    // TODO 현재의 방식은 출력의 방식을 변경하는 것이 쉽지 않다.
    // 별도의 도메인으로 관련 내용들을 옮기고
    // StringTemplate 활용해서 주입하자
    @Override
    public String toString() {
        return String.format(Locale.KOREAN, "%d개 일치%s (%,d원)", matches, bonus(), money);
    }
    
    // TODO 얘도 StringTemplate으로 주입
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
