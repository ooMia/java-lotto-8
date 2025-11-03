package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import lotto.TestUtil;
import org.junit.jupiter.api.Test;

class WinningStatsTest {
    private static Lotto lotto(int... numbers) {
        List<Integer> input = Arrays.stream(numbers).boxed().toList();
        return new Lotto(input);
    }

    @Test
    void testToString() {

        List<Lotto> input = List.of(
                lotto(8, 21, 23, 41, 42, 43),
                lotto(3, 5, 11, 16, 32, 38),
                lotto(7, 11, 16, 35, 36, 44),
                lotto(1, 8, 11, 31, 41, 42),
                lotto(13, 14, 16, 38, 42, 45),
                lotto(7, 11, 30, 40, 42, 43),
                lotto(2, 13, 22, 32, 38, 45),
                lotto(1, 3, 5, 14, 22, 45));

        WinnerLotto winner = new WinnerLotto(lotto(1, 2, 3, 4, 5, 6), 7);

        WinningStats stats = new WinningStats(input, winner);

        List<String> expected = List.of(
                "3개 일치 (5,000원) - 1개",
                "4개 일치 (50,000원) - 0개",
                "5개 일치 (1,500,000원) - 0개",
                "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                "6개 일치 (2,000,000,000원) - 0개",
                "총 수익률은 62.5%입니다.");

        assertThat(stats.toString()).contains(expected);
    }


    @Test
    void testProfitRate() {
        // (2_000_000_000 * 2) / (7_458 * 1_000) * 100 = 53633.68
        double totalProfit = 2_000_000_000L * 2;
        int numberTickets = 7_458;

        String expected = "53633.7%";
        double actualNumber = WinningStats.profitRate(totalProfit, numberTickets);
        String actual = Presentation.INSTANCE.statProfitRate(actualNumber);
        assertThat(actual).contains(expected);
    }

    @Test
    void testProfitRate_보유한_티켓이_없으면_오류() {
        TestUtil.GLOBAL.assertThatThrownBy(() -> WinningStats.profitRate(123, 0))
                .hasMessageContaining("PROFIT_DIV_ZERO");
    }

    @Test
    void testProfitRate_보유한_티켓이_음수라면_오류() {
        TestUtil.GLOBAL.assertThatThrownBy(() -> WinningStats.profitRate(123, -1))
                .hasMessageContaining("INVALID_STATE");
    }
}
