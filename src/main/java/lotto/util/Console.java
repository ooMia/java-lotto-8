package lotto.util;

import java.util.function.Function;

public interface Console {

    default int readInt() throws IllegalArgumentException {
        return parseNumber(Integer::parseInt, ConsoleProblem.PARSE_INTEGER_FAILED);
    }

    private <T> T parseNumber(Function<String, T> parser, ConsoleProblem ifFailed) {
        try {
            return parser.apply(readLine());
        } catch (NumberFormatException e) {
            throw Global.EXCEPTION_HANDLER.exception(ifFailed, e);
        }
    }

    String readLine();

    default long readUnsignedLong() throws IllegalArgumentException {
        return parseNumber(Long::parseUnsignedLong, ConsoleProblem.PARSE_UNSIGNED_LONG_FAILED);
    }

    default long readLong() throws IllegalArgumentException {
        return parseNumber(Long::parseLong, ConsoleProblem.PARSE_LONG_FAILED);
    }

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
