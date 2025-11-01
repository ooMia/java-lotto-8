package lotto;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.Vendor;
import lotto.domain.WinnerLotto;
import lotto.util.Console;
import lotto.util.ExceptionHandler;
import lotto.util.Tokenizer;

// TODO 불필요하게 인터페이스가 있는 것처럼 보인다.
// 어차피 다형성 필요 없으니까 그냥 구현체로 바꾸고
// 필요하면 전역 IoC 컨테이너에서 받아오는 식으로 편의성 확보해도 되고
public interface Controller {

    void buyLottos();

    void fetchWinner();

    void printStats();

}

class ControllerImpl implements Controller {

    private final Console console;
    private final ExceptionHandler handler;
    private final Tokenizer tokenizer;

    // TODO controller가 상태를 가지고 있는건 부자연스러워보여서
    // Domain 수준에서 서비스 하나 만들고
    // 걔 생성 후에 거기서 다 관리해도 되고?
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
