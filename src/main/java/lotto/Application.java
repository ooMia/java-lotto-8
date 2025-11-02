package lotto;

import java.util.List;
import lotto.domain.LotteryManager;
import lotto.util.Console;
import lotto.util.ExceptionHandler;
import lotto.util.Global;
import lotto.util.Tokenizer;

public class Application {
    public static void main(String[] args) {
        Global global = new Global() {};
        new Scenario(global).run();
    }

    static class Scenario implements Runnable {
        private final Console console;
        private final ExceptionHandler handler;
        private final Tokenizer tokenizer;

        public Scenario(Global global) {
            this.console = global.console();
            this.handler = global.exceptionHandler();
            this.tokenizer = global.tokenizer();
        }

        @Override
        public void run() {
            var manager = new LotteryManager();
            buyTickets(manager);
            fetchWinner(manager);
            printProfitStats(manager);
        }

        private void buyTickets(LotteryManager manager) {
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

        private void fetchWinner(LotteryManager manager) {
            var winnerTicket = handler.tryUntilValid(() -> {
                console.printLine("당첨 번호를 입력해 주세요.");
                String csvNumbers = console.readLine();
                List<Integer> numbers = tokenizer.split(csvNumbers, Integer::parseInt);
                return manager.prepareWinnerTicket(numbers);
            });
            console.printLine();
            handler.tryUntilValid(() -> {
                console.printLine("보너스 번호를 입력해 주세요.");
                int bonusNumber = console.readInt();
                return manager.completeWinnerTicket(winnerTicket, bonusNumber);
            });
            console.printLine();
        }

        private void printProfitStats(LotteryManager manager) {
            console.printLine("당첨 통계");
            console.printLine("---");
            console.printLine(manager.prizeStats());
        }
    }
}
