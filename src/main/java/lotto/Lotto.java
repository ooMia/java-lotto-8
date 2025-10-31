package lotto;

import java.util.List;
import java.util.Set;

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

    Prize toPrize(WinnerLotto winner) {
        Set<Integer> targetNumbers = Set.copyOf(this.numbers);
        int matches = (int) winner.lotto.numbers.stream().filter(targetNumbers::contains).count();
        boolean isBonusMatch = targetNumbers.contains(winner.bonusNumber);
        return Prize.of(matches, isBonusMatch);
    }
}
