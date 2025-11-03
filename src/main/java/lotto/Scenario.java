package lotto;

import java.util.List;
import lotto.domain.LottoManager;
import lotto.util.Console;
import lotto.util.ExceptionHandler;
import lotto.util.Global;
import lotto.util.Tokenizer;

class Scenario implements Runnable {

    private final Console console;
    private final ExceptionHandler handler;
    private final Tokenizer tokenizer;

    private final LottoManager manager;

    Scenario(Global global, LottoManager manager) {
        this.console = global.console();
        this.handler = global.exceptionHandler();
        this.tokenizer = global.tokenizer();

        this.manager = manager;
    }

    @Override
    public void run() {
        buyTickets();
        fetchWinner();
        printProfitStats();
    }

    private void buyTickets() {
        var tickets = handler.tryUntilValid(() -> {
            console.printLine("구입금액을 입력해 주세요.");
            var money = console.readInt();
            return manager.buyTickets(money);
        });
        console.printLine();
        console.printLine(String.format("%d개를 구매했습니다.", tickets.size()));
        tickets.forEach(console::printLine);
        console.printLine();
    }

    private void fetchWinner() {
        var winnerTicket = handler.tryUntilValid(() -> {
            console.printLine("당첨 번호를 입력해 주세요.");
            String csvNumbers = console.readLine();
            List<Integer> numbers = tokenizer.split(csvNumbers, Integer::parseInt);
            return manager.prepareWinnerTicket(numbers);
        });
        console.printLine();
        handler.tryUntilValid(() -> {
            console.printLine("보너스 번호를 입력해 주세요.");
            var bonusNumber = console.readInt();
            return manager.completeWinnerTicket(winnerTicket, bonusNumber);
        });
        console.printLine();
    }

    private void printProfitStats() {
        console.printLine("당첨 통계");
        console.printLine("---");
        console.printLine(manager.prizeStats());
    }
}
