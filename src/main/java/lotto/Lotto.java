package lotto;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        LottoRule.NumberLengthRule.DEFAULT.validate(numbers);
        numbers.stream().forEach(LottoRule.NumberRangeRule.DEFAULT::validate);
    }

    boolean isContain(int number) {
        return this.numbers.contains(number);
    }
}
