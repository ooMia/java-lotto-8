package lotto;

import lotto.util.Console;
import lotto.util.ExceptionHandler;
import lotto.util.Tokenizer;

public final class Global {

    public static final Console CONSOLE = camp.nextstep.edu.missionutils.Console::readLine;

    private static final String ERROR_PREFIX = "[ERROR] ";
    public static final ExceptionHandler EXCEPTION_HANDLER = new ExceptionHandler(CONSOLE, ERROR_PREFIX);

    private static final char DEFAULT_DELIMITER = ',';
    public static final Tokenizer TOKENIZER = new Tokenizer(DEFAULT_DELIMITER);

    private Global() {
    }

}
