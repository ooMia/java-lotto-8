package lotto;

import java.util.List;

public class WinnerLotto extends Lotto {

    private final int bonusNumber;

    public WinnerLotto(List<Integer> numbers, int bonusNumber) {
        super(numbers);
        this.bonusNumber = bonusNumber;
        validate();
    }

    private void validate() {
        LottoRule.NumberRangeRule.DEFAULT.validate(this.bonusNumber);
        if (super.isContain(this.bonusNumber)) {
            throw LottoProblem.DUPLITCATE_NUMBER.exception();
        }
    }
}
