package lotto.domain;

// TODO consider changing to record
// TODO to package private
public final class WinnerLotto {
    // TODO 이거 왜 public임?
    // 아 지금은 Lotto가 참조하고 있어서 그렇고
    // 나중에 toPrize 메서드 들여오면 수정하면 됨
    public Lotto lotto;
    public int bonusNumber;

    public WinnerLotto(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
        validate();
    }

    private void validate() {
        LottoRule.NumberRangeRule.DEFAULT.validate(this.bonusNumber);
        if (this.lotto.isContain(this.bonusNumber)) {
            throw LottoProblem.DUPLITCATE_NUMBER.exception();
        }
    }

    Prize toPrize(Lotto winner) {
        // Set<Integer> targetNumbers = Set.copyOf(this.numbers);
        // int matches = (int)
        // winner.lotto.numbers.stream().filter(targetNumbers::contains).count();
        // boolean isBonusMatch = targetNumbers.contains(winner.bonusNumber);
        // return Prize.of(matches, isBonusMatch);
        // TODO implement
        throw new UnsupportedOperationException();
    }
}
