package lotto;

import lotto.util.Global;

public class Application {
    public static void main(String[] args) {
        var history = new Controller(
                Global.CONSOLE,
                Global.EXCEPTION_HANDLER,
                Global.TOKENIZER);
        history.buyLottos();
        history.fetchWinner();
        history.printStats();
    }
}
