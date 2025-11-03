package lotto.util;

import java.util.function.Function;

public interface Console {

    default int readInt() throws IllegalArgumentException {
        return parseNumber(Integer::parseInt);
    }

    private <T> T parseNumber(Function<String, T> parser) {
        try {
            return parser.apply(readLine());
        } catch (NumberFormatException e) {
            throw ConsoleProblem.PARSE_NUMBER_FAILED.exception(e);
        }
    }

    String readLine();

    default void printLine(Object message) {
        printLine(message.toString());
    }

    default void printLine(String message) {
        System.out.println(message);
    }

    default void printLine() {
        System.out.println();
    }
}
