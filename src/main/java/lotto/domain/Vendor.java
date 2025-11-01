package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.LottoRule.NumberLengthRule;
import lotto.domain.LottoRule.NumberRangeRule;

final class Vendor {

    private final int lottoPrice;

    public Vendor(int lottoPrice) {
        this.lottoPrice = lottoPrice;
    }

    List<Lotto> buyLotto(int money) {
        validate(money);
        int nLottos = money / lottoPrice;
        var lottos = new ArrayList<Lotto>();
        for (var iter = 0; iter < nLottos; ++iter) {
            lottos.add(generateRandomLotto());
        }
        return lottos;
    }

    private void validate(int money) {
        if (money < lottoPrice) {
            throw LottoProblem.NOT_ENOUGH_MONEY.exception();
        }
        if (money % lottoPrice != 0) {
            throw LottoProblem.MOD_PRICE_NOT_ZERO.exception();
        }
    }

    private static Lotto generateRandomLotto() {
        int startInclusive = NumberRangeRule.DEFAULT.minInclusive();
        int endInclusive = NumberRangeRule.DEFAULT.maxInclusive();
        int count = NumberLengthRule.DEFAULT.uniquesExactly();
        return new Lotto(Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count));
    }
}
