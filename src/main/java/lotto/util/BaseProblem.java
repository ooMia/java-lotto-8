package lotto.util;

enum BaseProblem {
    PARSE_LONG_FAILED,
    PARSE_INTEGER_FAILED,;

    private static final String ERROR_PREFIX = "[ERROR]";

    IllegalArgumentException exception() {
        return new IllegalArgumentException(message());
    }

    IllegalArgumentException exception(Throwable e) {
        return new IllegalArgumentException(message(), e);
    }

    private String message(){
        return String.format("%s %s", ERROR_PREFIX, this.name());
    }

}
