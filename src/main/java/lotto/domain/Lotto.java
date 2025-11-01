package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TODO consider changing to package private
public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    private static void validate(List<Integer> numbers) {
        LottoRule.NumberLengthRule.DEFAULT.validate(numbers);
        numbers.stream().forEach(LottoRule.NumberRangeRule.DEFAULT::validate);
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
