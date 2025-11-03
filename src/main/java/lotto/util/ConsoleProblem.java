package lotto.util;

public enum ConsoleProblem implements BaseProblem {
    PARSE_NUMBER_FAILED,
    NULL_POINTER;

    @Override
    public String message() {
        return this.name();
    }
}
