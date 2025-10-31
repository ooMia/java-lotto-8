package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class WinningStatsTest {
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

    private static Lotto lotto(int... numbers) {
        List<Integer> input = Arrays.stream(numbers).boxed().toList();
        return new Lotto(input);
    }
}
