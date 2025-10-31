package lotto.util;

public interface Console {

    default int readInt() throws IllegalArgumentException {
        try {
            return Integer.parseInt(readLine());
        } catch (NumberFormatException e) {
            throw BaseProblem.PARSE_INTEGER_FAILED.exception(e);
        }
    }

    String readLine();

    default long readLong() throws IllegalArgumentException {
        try {
            return Long.parseLong(readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
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
