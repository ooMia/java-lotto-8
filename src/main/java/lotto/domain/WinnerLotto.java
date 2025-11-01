package lotto.domain;

// TODO consider changing to record
// TODO to package private
public final class WinnerLotto {
    
    private Lotto lotto;
    private int bonusNumber;

    public WinnerLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
        validate();
    }

    private void validate() {
        LottoRule.NumberRangeRule.DEFAULT.validate(this.bonusNumber);
        if (this.lotto.contains(this.bonusNumber)) {
            throw LottoProblem.DUPLITCATE_NUMBER.exception();
        }
    }

    Prize toPrize(Lotto guess) {
        int matches = this.lotto.countMatches(guess);
        boolean isBonusMatch = guess.contains(bonusNumber);
        return Prize.ofResult(matches, isBonusMatch);
    }
}
