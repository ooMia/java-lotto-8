package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

final class Vendor {
    private static final int LOTTO_PRICE = LotteryManager.LOTTO_PRICE;

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
        int startInclusive = NumberRangeRule.DEFAULT.minInclusive();
        int endInclusive = NumberRangeRule.DEFAULT.maxInclusive();
        int count = NumberLengthRule.DEFAULT.uniquesExactly();
        return new Lotto(Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count));
    }

    record NumberRangeRule(int minInclusive, int maxInclusive) {
        static final NumberRangeRule DEFAULT = new NumberRangeRule(1, 45);

        void validate(List<Integer> numbers) {
            numbers.forEach(this::validate);
        }

        void validate(int number) {
            if (number < minInclusive || maxInclusive < number) {
                throw LottoProblem.NUMBER_OUT_OF_RANGE.exception();
            }
        }
    }

    record NumberLengthRule(int uniquesExactly) {
        static final NumberLengthRule DEFAULT = new NumberLengthRule(6);

        void validate(List<Integer> numbers) {
            if (numbers.size() != uniquesExactly) {
                throw LottoProblem.NUMBERS_LENGTH_NOT_SIX.exception();
            }

            Set<Integer> uniques = Set.copyOf(numbers);
            if (uniques.size() != uniquesExactly) {
                throw LottoProblem.DUPLICATE_NUMBER.exception();
            }
        }
    }
}
