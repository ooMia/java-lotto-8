package lotto.domain;

import java.util.Locale;

interface Presentation {
    Presentation instance = new PresentationImpl();

    String prize(int matches, boolean isMatchFiveWithBonus, int money);

    String statPrizeCount(Prize prize, int count);

    String statProfitRate(double profitRate);
}

class PresentationImpl implements Presentation {

    @Override
    public String prize(int matches, boolean isMatchFiveWithBonus, int money) {
        String bonus = "";
        if (isMatchFiveWithBonus) {
            bonus = ", 보너스 볼 일치";
        }
        return format("%d개 일치%s (%,d원)", matches, bonus, money);
    }

    @Override
    public String statPrizeCount(Prize prize, int count) {
        return format("%s - %d개", prize, count);
    }

    @Override
    public String statProfitRate(double profitRate) {
        return format("총 수익률은 %.1f%%입니다.", profitRate);
    }

    private String format(String format, Object... args) {
        return String.format(Locale.KOREAN, format, args);
    }
}
