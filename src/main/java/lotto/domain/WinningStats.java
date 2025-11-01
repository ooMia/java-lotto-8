package lotto.domain;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

class WinningStats {

    private static final int TO_PERCENTAGE = 100;

    private final SortedMap<Prize, Integer> prizeCount = new TreeMap<>(Prize.comparator().reversed());
    private final double profitRate;

    WinningStats(List<Lotto> lottos, WinnerLotto winner) {
        for (Prize values : Prize.values()) {
            prizeCount.put(values, 0);
        }
        lottos.stream()
                .map(winner::toPrize)
                .forEach(prize -> prizeCount.put(prize, prizeCount.get(prize) + 1));
        this.profitRate = profitRate(totalProfit(), lottos.size());
        prizeCount.remove(Prize.MATCH_NONE);
    }

    private double totalProfit() {
        return prizeCount.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().money * entry.getValue())
                .sum();
    }

    private static double profitRate(double totalProfit, int numberLottos) {
        if (numberLottos <= 0 || Service.LOTTO_PRICE <= 0) {
            throw LottoProblem.PROFIT_DIV_ZERO.exception();
        }
        return totalProfit * TO_PERCENTAGE / (numberLottos * Service.LOTTO_PRICE);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (var e : prizeCount.entrySet()) {
            sb.append(StringTemplate.statPrizeCount(e.getKey(), e.getValue()));
            sb.append(System.lineSeparator());
        }
        sb.append(StringTemplate.statProfitRate(profitRate));
        return sb.toString();
    }
}
