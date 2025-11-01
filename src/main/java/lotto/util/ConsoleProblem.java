package lotto.util;

public enum ConsoleProblem implements BaseProblem {
    PARSE_LONG_FAILED,
    PARSE_INTEGER_FAILED,
    ;

    @Override
    public String message() {
        return this.name();
    }

    @Override
    public RuntimeException exception() {
        return Global.EXCEPTION_HANDLER.exception(this);
    }
}
