package lotto.domain;

import java.util.List;
import java.util.Locale;
import java.util.Map;
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
                .map(lotto -> winner.toPrize(lotto))
                .forEach(prize -> prizeCount.put(prize, prizeCount.get(prize) + 1));
        this.profitRate = profitRate(totalProfit(), lottos.size());
        // TODO 생성자에서 완성하고 이후에 특별히 수정이 없으니까 NONE에 해당하는 키를 빼버리면 이후 로직을 간결하게 가져갈 수 있다.
    }

    // TODO profitRate을 좀 더 깔끔하게 계산할 수 있는 방법 없을까? 
    // 일단 totalProfit을 double로 만들어 그 다음에 뒤에를 나눠 그러면 좀 더 깔끔해질지도
    private long totalProfit() {
        long totalProfit = 0;
        for (var e : prizeCount.entrySet()) {
            totalProfit += (long) e.getKey().money * e.getValue();
        }
        return totalProfit;
    }

    private static double profitRate(long totalProfit, int numberLottos) {
        if (numberLottos == 0) {
            return 0.;
        }
        return (double) totalProfit * TO_PERCENTAGE / (numberLottos * Service.LOTTO_PRICE);
    }

    // TODO 얘도 StringTemplate으로 주입
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
