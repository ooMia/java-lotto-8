package lotto.domain;

import java.util.Locale;

class StringTemplate {

    static String prize(int matches, boolean isMatchFiveWithBonus, int money) {
        var bonusString = "";
        if (isMatchFiveWithBonus) {
            bonusString = ", 보너스 볼 일치";
        }
        return String.format(Locale.KOREAN, "%d개 일치%s (%,d원)", matches, bonusString, money);
    }

    static String statPrizeCount(Prize prize, int count) {
        return String.format(Locale.KOREAN, "%s - %d개", prize.toString(), count);
    }

    static String statProfitRate(double profitRate) {
        return String.format(Locale.KOREAN, "총 수익률은 %.1f%%입니다.", profitRate);
    }
}
