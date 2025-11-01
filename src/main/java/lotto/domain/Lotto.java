package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    Lotto(List<Integer> numbers) {
        this.numbers = List.copyOf(numbers);
        validate();
    }

    private void validate() {
        LottoRule.NumberLengthRule.DEFAULT.validate(this.numbers);
        numbers.forEach(LottoRule.NumberRangeRule.DEFAULT::validate);
    }

    boolean contains(int number) {
        return this.numbers.contains(number);
    }

    int countMatches(Lotto lotto) {
        int base = this.numbers.size() + lotto.numbers.size();
        Set<Integer> uniques = new HashSet<>();
        uniques.addAll(this.numbers);
        uniques.addAll(lotto.numbers);
        return base - uniques.size();
    }

    @Override
    public String toString() {
        return this.numbers.toString();
    }
}
