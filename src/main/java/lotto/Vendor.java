package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.LottoRule.NumberLengthRule;
import lotto.LottoRule.NumberRangeRule;
import lotto.util.Console;

public class Vendor {

    public static final int LOTTO_PRICE = 1_000;

    private final List<Lotto> lottos = new ArrayList<>();

    public Vendor(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw LottoProblem.MOD_PRICE_NOT_ZERO.exception();
        }
        int nLottos = money / LOTTO_PRICE;
        for (var iter = 0; iter < nLottos; ++iter) {
            this.lottos.add(generateRandomLotto());
        }
    }

    private static Lotto generateRandomLotto() {
        int startInclusive = NumberRangeRule.DEFAULT.minInclusive();
        int endInclusive = NumberRangeRule.DEFAULT.maxInclusive();
        int count = NumberLengthRule.DEFAULT.uniquesExactly();
        return new Lotto(Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count));
    }

    WinningStats result(WinnerLotto winner) {
        return new WinningStats(this.lottos, winner);
    }

    public void printLotto(Console console) {
        console.printLine(String.format("%d개를 구매했습니다.", lottos.size()));
        this.lottos.forEach(console::printLine);
    }

}
