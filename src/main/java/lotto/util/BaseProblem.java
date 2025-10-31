package lotto.util;

enum BaseProblem {
    PARSE_LONG_FAILED,
    PARSE_INTEGER_FAILED,
    ;

    private static final String ERROR_PREFIX = "[ERROR]";

    IllegalArgumentException exception() {
        return new IllegalArgumentException(message());
    }

    private String message() {
        return String.format("%s %s", ERROR_PREFIX, this.name());
    }

    IllegalArgumentException exception(Throwable e) {
        return new IllegalArgumentException(message(), e);
    }

}
