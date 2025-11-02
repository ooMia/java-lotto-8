package lotto.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public class ExceptionHandler {

    private final Console console = Global.CONSOLE;

    private final Constructor<? extends RuntimeException> baseStringConstructor;
    private final Constructor<? extends RuntimeException> baseStringThrowableConstructor;

    public ExceptionHandler(Class<? extends RuntimeException> baseException) {
        try {
            this.baseStringConstructor = baseException.getConstructor(String.class);
            this.baseStringThrowableConstructor = baseException.getConstructor(String.class, Throwable.class);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public <T> T throwIfInvalid(Supplier<T> supplier) throws IllegalArgumentException {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            console.printLine(e.getMessage());
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

    public RuntimeException exception(BaseProblem cause) {
        return toBaseException(prefixMessage(cause));
    }

    public RuntimeException exception(BaseProblem cause, Throwable e) {
        return toBaseException(prefixMessage(cause), e);
    }

    private String prefixMessage(BaseProblem cause) {
        return Global.ERROR_PREFIX + cause.message();
    }

    private RuntimeException toBaseException(String message) {
        try {
            return baseStringConstructor.newInstance(message);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(message, e);
        }
    }

    private RuntimeException toBaseException(String message, Throwable cause) {
        try {
            return baseStringThrowableConstructor.newInstance(message, cause);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(message, e);
        }
    }
}
