package lotto.util;

public interface BaseProblem {
    String message();

    default RuntimeException exception() {
        return Global.EXCEPTION_HANDLER.exception(this);
    }

    default RuntimeException exception(Throwable cause) {
        return Global.EXCEPTION_HANDLER.exception(this, cause);
    }
}
