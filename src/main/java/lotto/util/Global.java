package lotto.util;

public interface Global {
    Global INSTANCE = new Global() {
    };

    Console CONSOLE = camp.nextstep.edu.missionutils.Console::readLine;

    String ERROR_PREFIX = "[ERROR] ";
    ExceptionHandler EXCEPTION_HANDLER = new ExceptionHandler(CONSOLE, ERROR_PREFIX);

    char DEFAULT_DELIMITER = ',';
    Tokenizer TOKENIZER = new Tokenizer(DEFAULT_DELIMITER);

    default Console console() {
        return CONSOLE;
    }

    default ExceptionHandler exceptionHandler() {
        return EXCEPTION_HANDLER;
    }

    default Tokenizer tokenizer() {
        return TOKENIZER;
    }
}
