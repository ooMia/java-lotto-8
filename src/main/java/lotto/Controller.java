package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Service;
import lotto.domain.WinnerLotto;
import lotto.util.Console;
import lotto.util.ExceptionHandler;
import lotto.util.Tokenizer;

class Controller {
    private final Console console;
    private final ExceptionHandler handler;
    private final Tokenizer tokenizer;

    // TODO 상태 간소화
    private final Service service = new Service();
    private List<Lotto> lottos;
    private WinnerLotto winner;

    public Controller(Console console, ExceptionHandler handler, Tokenizer tokenizer) {
        this.console = console;
        this.handler = handler;
        this.tokenizer = tokenizer;
    }

    public void buyLottos() {

        console.printLine("구입금액을 입력해 주세요.");
        this.lottos = handler.tryUntilValid(() -> {
            var money = console.readInt();
            return service.buyLottos(money);
        });
        console.printLine();

        console.printLine(String.format("%d개를 구매했습니다.", this.lottos.size()));
        this.lottos.forEach(console::printLine);
        console.printLine();
    }

    public void fetchWinner() {
        console.printLine("당첨 번호를 입력해 주세요.");
        Lotto lotto = handler.tryUntilValid(() -> {
            var csvNumbers = console.readLine();
            List<Integer> numbers = this.tokenizer.split(csvNumbers, Integer::parseInt);
            return service.prepareWinnerLotto(numbers);
        });
        console.printLine();

        console.printLine("보너스 번호를 입력해 주세요.");
        this.winner = handler.tryUntilValid(() -> {
            var number = console.readInt();
            return service.completeWinnerLotto(lotto, number);
        });
        console.printLine();
    }

    public void printStats() {
        console.printLine("당첨 통계");
        console.printLine("---");
        console.printLine(service.toStatistics(this.lottos, this.winner));
    }

}
