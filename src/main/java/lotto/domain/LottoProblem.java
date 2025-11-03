package lotto.domain;

import lotto.util.BaseProblem;

enum LottoProblem implements BaseProblem {

    NUMBER_OUT_OF_RANGE,
    MOD_PRICE_NOT_ZERO,
    DUPLICATE_NUMBER,
    NUMBERS_LENGTH_NOT_SIX,
    NOT_ENOUGH_MONEY,
    PROFIT_DIV_ZERO,
    REQUEST_EXCEED_MEMORY_LIMITATION,
    INVALID_STATE;

    @Override
    public String message() {
        return this.name();
    }
}
