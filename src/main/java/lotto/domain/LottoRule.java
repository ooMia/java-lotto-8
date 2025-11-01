package lotto.domain;

import java.util.List;
import java.util.Set;

final class LottoRule {

    private LottoRule() {
    }

    record NumberLengthRule(int uniquesExactly) {
        static final NumberLengthRule DEFAULT = new NumberLengthRule(6);

        void validate(List<Integer> numbers) {
            if (numbers.size() != uniquesExactly) {
                throw LottoProblem.NUMBERS_LENGTH_NOT_SIX.exception();
            }

            Set<Integer> uniques = Set.copyOf(numbers);
            if (uniques.size() != uniquesExactly) {
                throw LottoProblem.DUPLITCATE_NUMBER.exception();
            }
        }
    }

    record NumberRangeRule(int minInclusive, int maxInclusive) {
        static final NumberRangeRule DEFAULT = new NumberRangeRule(1, 45);

        void validate(int number) {
            if (number < minInclusive || maxInclusive < number) {
                throw LottoProblem.NUMBER_OUT_OF_RANGE.exception();
            }
        }
    }
}
