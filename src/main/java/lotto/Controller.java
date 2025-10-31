package lotto;

import java.util.List;
import lotto.util.Console;
import lotto.util.ExceptionHandler;
import lotto.util.Tokenizer;

public interface Controller {

    void buyLottos();

    void fetchWinner();

    void printStats();

}

class ControllerImpl implements Controller {

    private final Console console;
    private final ExceptionHandler handler;
    private final Tokenizer tokenizer;

    private Vendor vendor;
    private WinnerLotto winner;

    public ControllerImpl(Console console, ExceptionHandler handler, Tokenizer tokenizer) {
        this.console = console;
        this.handler = handler;
        this.tokenizer = tokenizer;
    }

    @Override
    public void buyLottos() {

        console.printLine("구입금액을 입력해 주세요.");
        this.vendor = handler.tryUntilValid(() -> {
            var money = console.readInt();
            return new Vendor(money);
        });
        console.printLine();

        this.vendor.printLotto(console);
        console.printLine();
    }

    @Override
    public void fetchWinner() {
        console.printLine("당첨 번호를 입력해 주세요.");
        Lotto lotto = handler.tryUntilValid(() -> {
            var csvNumbers = console.readLine();
            List<Integer> numbers = this.tokenizer.split(csvNumbers, Integer::parseInt);
            return new Lotto(numbers);
        });
        console.printLine();

        console.printLine("보너스 번호를 입력해 주세요.");
        this.winner = handler.tryUntilValid(() -> {
            var number = console.readInt();
            return new WinnerLotto(lotto, number);
        });
        console.printLine();
    }

    @Override
    public void printStats() {
        console.printLine("당첨 통계");
        console.printLine("---");
        console.printLine(this.vendor.result(winner));
    }

}
