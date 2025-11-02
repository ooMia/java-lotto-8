package lotto.util;

public enum ConsoleProblem implements BaseProblem {
    PARSE_LONG_FAILED,
    PARSE_INTEGER_FAILED,
    PARSE_UNSIGNED_LONG_FAILED,
    ;

    @Override
    public String message() {
        return this.name();
    }
}
