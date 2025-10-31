package lotto;

public class WinnerLotto {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinnerLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
        validate();
    }

    private void validate() {
        LottoRule.NumberRangeRule.DEFAULT.validate(this.bonusNumber);
        if (lotto.isContain(this.bonusNumber)) {
            throw LottoProblem.DUPLITCATE_NUMBER.exception();
        }
    }
}
