package lotto.domain;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

class WinningStats {
    private static final int LOTTO_PRICE = LotteryManager.LOTTO_PRICE;
    private static final int TO_PERCENTAGE = 100;

    private final SortedMap<Prize, Integer> prizeCount = new TreeMap<>(Prize.comparator().reversed());
    private final double profitRate;

    WinningStats(Collection<Lotto> tickets, WinnerLotto winner) {
        for (Prize values : Prize.values()) {
            prizeCount.put(values, 0);
        }
        tickets.stream()
                .map(winner::toPrize)
                .forEach(prize -> prizeCount.put(prize, prizeCount.get(prize) + 1));
        this.profitRate = profitRate(totalProfit(), tickets.size());
        prizeCount.remove(Prize.MATCH_NONE);
    }

    private double totalProfit() {
        return prizeCount.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().money * entry.getValue())
                .sum();
    }

    private static double profitRate(double totalProfit, int numberTickets) {
        if (numberTickets <= 0 || LOTTO_PRICE <= 0) {
            throw LottoProblem.PROFIT_DIV_ZERO.exception();
        }
        return totalProfit * TO_PERCENTAGE / (numberTickets * LOTTO_PRICE);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (var e : prizeCount.entrySet()) {
            sb.append(Presentation.instance.statPrizeCount(e.getKey(), e.getValue()));
            sb.append(System.lineSeparator());
        }
        sb.append(Presentation.instance.statProfitRate(profitRate));
        return sb.toString();
    }
}
