package lotto.domain;

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

    boolean isContain(int number) {
        return this.numbers.contains(number);
    }

    // TODO 더 낮은 수준으로 존재하는 Lotto보다
    // WinnerLotto가 이 메서드를 가지고 있는게 더 합당해보임
    Prize toPrize(WinnerLotto winner) {
        Set<Integer> targetNumbers = Set.copyOf(this.numbers);
        int matches = (int) winner.lotto.numbers.stream().filter(targetNumbers::contains).count();
        boolean isBonusMatch = targetNumbers.contains(winner.bonusNumber);
        return Prize.of(matches, isBonusMatch);
    }

    @Override
    public String toString() {
        return this.numbers.toString();
    }
}
