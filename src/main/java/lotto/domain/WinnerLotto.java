package lotto.domain;

import lotto.domain.Vendor.NumberRangeRule;

public class WinnerLotto {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinnerLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
        validate();
    }

    private void validate() {
        NumberRangeRule.DEFAULT.validate(bonusNumber);
        if (lotto.contains(bonusNumber)) {
            throw LottoProblem.DUPLICATE_NUMBER.exception();
        }
    }

    Prize toPrize(Lotto guess) {
        int matches = this.lotto.countMatches(guess);
        boolean isBonusMatch = guess.contains(bonusNumber);
        return Prize.ofResult(matches, isBonusMatch);
    }
}
