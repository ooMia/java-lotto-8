package lotto.domain;

import java.util.Locale;

interface Presentation {

    Presentation INSTANCE = new Presentation() {
    };

    default String prize(int matches, boolean isMatchFiveWithBonus, int money) {
        String bonus = "";
        if (isMatchFiveWithBonus) {
            bonus = ", 보너스 볼 일치";
        }
        return format("%d개 일치%s (%,d원)", matches, bonus, money);
    }

    default String statPrizeCount(Prize prize, int count) {
        return format("%s - %d개", prize, count);
    }

    default String statProfitRate(double profitRate) {
        return format("총 수익률은 %.1f%%입니다.", profitRate);
    }

    private String format(String format, Object... args) {
        return String.format(Locale.KOREAN, format, args);
    }
}

