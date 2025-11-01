package lotto.util;

import java.util.function.Supplier;

public class ExceptionHandler {
    private final String errorPrefix;
    private final Console console;

    public ExceptionHandler(Console console, String errorPrefix) {
        this.console = console;
        this.errorPrefix = errorPrefix;
    }

    public <T> T throwIfInvalid(Supplier<T> supplier) throws IllegalArgumentException {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            console.printLine(errorPrefix + e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    public <T> T tryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                console.printLine(errorPrefix + e.getMessage());
            }
        }
    }

    public IllegalArgumentException exception(BaseProblem cause) {
        return new IllegalArgumentException(errorPrefix + cause.toString());
    }
}
