package lotto;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.LottoRule.NumberLengthRule;
import lotto.LottoRule.NumberRangeRule;

public class Vendor {

    public static final int LOTTO_PRICE = 1_000;
    
    private final List<Lotto> lottos = new ArrayList<>();

    public Vendor(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw LottoProblem.MOD_PRICE_NOT_ZERO.exception();
        }
        int nLottos = money / LOTTO_PRICE;
        for (var iter = 0; iter < nLottos; ++iter){
            this.lottos.add(generateRandomLotto());
        }
    }

    private static Lotto generateRandomLotto() {
        int startInclusive = NumberRangeRule.DEFAULT.minInclusive();
        int endInclusive = NumberRangeRule.DEFAULT.maxInclusive();
        int count = NumberLengthRule.DEFAULT.uniquesExactly();
        return new Lotto(Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count));
    }

}
