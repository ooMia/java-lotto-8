package lotto;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class WinningStats {

    private static final int TO_PERCENTAGE = 100;

    // reversed natural order
    private final Map<Prize, Integer> prizeCount = new TreeMap<>((arg0, arg1) -> arg1.compareTo(arg0));
    private final double profitRate;

    public WinningStats(List<Lotto> lottos, WinnerLotto winner) {
        for (Prize values : Prize.values()) {
            prizeCount.put(values, 0);
        }
        lottos.stream()
                .map(lotto -> lotto.toPrize(winner))
                .forEach(prize -> prizeCount.put(prize, prizeCount.get(prize) + 1));
        this.profitRate = totalProfit() * TO_PERCENTAGE / (lottos.size() * Vendor.LOTTO_PRICE);
    }

    private double totalProfit() {
        long totalProfit = 0;
        for (var e : prizeCount.entrySet()) {
            totalProfit += (long) e.getKey().money * e.getValue();
        }
        return totalProfit;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (var e : prizeCount.entrySet()) {
            var key = e.getKey();
            if (key == Prize.MATCH_NONE) {
                continue;
            }
            sb.append(key.toString());
            sb.append(String.format(" - %d개", e.getValue()));
            sb.append(System.lineSeparator());
        }
        sb.append(String.format(Locale.KOREAN, "총 수익률은 %.1f%%입니다.", this.profitRate));
        return sb.toString();
    }

}
