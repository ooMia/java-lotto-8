package lotto;


// TODO consider changing to record
public class WinnerLotto {
    public Lotto lotto;
    public int bonusNumber;

    public WinnerLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
        validate();
    }

    private void validate() {
        if (this.lotto.isContain(this.bonusNumber)) {
            throw LottoProblem.DUPLITCATE_NUMBER.exception();
        }
        LottoRule.NumberRangeRule.DEFAULT.validate(this.bonusNumber);
    }
}
