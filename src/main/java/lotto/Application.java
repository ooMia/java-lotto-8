package lotto;

public class Application {
    public static void main(String[] args) {
        var history = new ControllerImpl(
                Global.CONSOLE,
                Global.EXCEPTION_HANDLER,
                Global.TOKENIZER);
        history.buyLottos();
        history.fetchWinner();
        history.printStats();
    }
}
