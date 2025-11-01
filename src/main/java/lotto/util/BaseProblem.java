package lotto.util;

enum BaseProblem {
    PARSE_LONG_FAILED,
    PARSE_INTEGER_FAILED,
    ;

    // TODO 이거 전부 ExceptionHandler로 처리하기
    private static final String ERROR_PREFIX = "[ERROR]";

    IllegalArgumentException exception() {
        return new IllegalArgumentException(this.toString());
        // return Global.EXCEPTION_HANDLER.of(this).log().raiseException();
    }

    @Override
    public String toString() {
        return String.format("%s %s", ERROR_PREFIX, this.name());
    }

    IllegalArgumentException exception(Throwable e) {
        return new IllegalArgumentException(this.toString(), e);
    }

}
