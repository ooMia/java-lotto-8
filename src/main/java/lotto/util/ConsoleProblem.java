package lotto.util;

public enum ConsoleProblem implements BaseProblem {
    PARSE_LONG_FAILED,
    PARSE_INTEGER_FAILED,
    ;

    @Override
    public String message() {
        return this.name();
    }
}
