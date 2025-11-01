package lotto.domain;

import lotto.util.BaseProblem;
import lotto.util.Global;

enum LottoProblem implements BaseProblem {

    NUMBER_OUT_OF_RANGE,
    MOD_PRICE_NOT_ZERO,
    DUPLITCATE_NUMBER,
    NUMBERS_LENGTH_NOT_SIX,
    NOT_ENOUGH_MONEY;

    @Override
    public String message() {
        return this.name();
    }

    @Override
    public RuntimeException exception() {
        return Global.EXCEPTION_HANDLER.exception(this);
    }
}
