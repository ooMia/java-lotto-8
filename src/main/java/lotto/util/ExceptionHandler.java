package lotto.util;

import java.util.function.Supplier;

public class ExceptionHandler {
    private final String errorPrefix;
    private final Console console;

    public ExceptionHandler(Console console, String errorPrefix) {
        this.console = console;
        this.errorPrefix = errorPrefix;
    }

    public Handler of(BaseProblem cause) {
        return new Handler(this.console, cause);
    }

    public <T> T throwIfInvalid(Supplier<T> supplier) throws IllegalArgumentException {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public <T> T tryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                console.printLine(e.getMessage());
            }
        }
    }

    public class Handler {
        private final Console console;
        private final BaseProblem cause;

        public Handler(Console console, BaseProblem cause) {
            this.console = console;
            this.cause = cause;
        }

        public Handler log() {
            this.console.printLine(errorPrefix + cause.toString());
            return this;
        }

        public void raiseException(Throwable e) throws IllegalArgumentException {
            throw cause.exception();
        }
    }
}
