package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

final class Vendor {

    private static final int LOTTO_PRICE = LottoManager.LOTTO_PRICE;

    private Vendor() {
    }

    static List<Lotto> buyLotto(long money) {
        validate(money);
        return limitedOfferOnly(money);
    }

    private static void validate(long money) {
        if (money < LOTTO_PRICE) {
            throw LottoProblem.NOT_ENOUGH_MONEY.exception();
        }
        if (money % LOTTO_PRICE != 0) {
            throw LottoProblem.MOD_PRICE_NOT_ZERO.exception();
        }
    }

    private static List<Lotto> limitedOfferOnly(long money) {
        try {
            var size = Math.toIntExact(money / LOTTO_PRICE);
            var container = new ArrayList<Lotto>(size);
            while (container.size() < size) {
                container.add(generateRandomLotto());
            }
            return container;
        } catch (ArithmeticException | OutOfMemoryError e) {
            throw LottoProblem.REQUEST_EXCEED_MEMORY_LIMITATION.exception();
        }
    }

    private static Lotto generateRandomLotto() {
        int startInclusive = LottoRule.NumberRangeRule.DEFAULT.minInclusive();
        int endInclusive = LottoRule.NumberRangeRule.DEFAULT.maxInclusive();
        int count = LottoRule.NumberLengthRule.DEFAULT.uniquesExactly();
        return new Lotto(Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count));
    }

}
