package lotto;

import lotto.util.BaseProblem;
import lotto.util.Global;

enum LottoProblem implements BaseProblem {
    PARSE_LONG_FAILED,
    PARSE_INTEGER_FAILED,

    // 로또 [1,45] 7개에 전부 쓰고, 받는 금액에도 쓸 수 있음
    NUMBER_OUT_OF_RANGE,

    // 1,000원으로 나누어 떨어지지 않는 경우
    MOD_PRICE_NOT_ZERO,

    // 로또할 때도 쓰고, 보너스 번호에도 사용 가능
    DUPLITCATE_NUMBER,
    NUMBERS_LENGTH_NOT_SIX, NOT_ENOUGH_MONEY;

    @Override
    public String message() {
        return this.name();
    }

    @Override
    public RuntimeException exception() {
        return Global.EXCEPTION_HANDLER.exception(this);
    }

}
