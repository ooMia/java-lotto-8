package lotto.util;

public enum ConsoleProblem implements BaseProblem {
    PARSE_LONG_FAILED,
    PARSE_INTEGER_FAILED,
    PARSE_UNSIGNED_LONG_FAILED,
    NULL_POINTER;

    @Override
    public String message() {
        return this.name();
    }
}
