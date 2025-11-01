package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.LottoRule.NumberLengthRule;
import lotto.LottoRule.NumberRangeRule;
import lotto.util.Console;

public class Vendor {

    public static final int LOTTO_PRICE = 1_000;

    // TODO vendor가 상태를 가지고 있는 것보다
    // 그냥 이 친구의 호출자 수준에서 관리하는 게 더 자연스러워보임
    private final List<Lotto> lottos = new ArrayList<>();

    public Vendor(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw LottoProblem.MOD_PRICE_NOT_ZERO.exception();
        }
        int nLottos = money / LOTTO_PRICE;
        for (var iter = 0; iter < nLottos; ++iter) {
            this.lottos.add(generateRandomLotto());
        }
    }

    // TODO package private로 바꾸고 발급해주는 것에 집중
    private static Lotto generateRandomLotto() {
        int startInclusive = NumberRangeRule.DEFAULT.minInclusive();
        int endInclusive = NumberRangeRule.DEFAULT.maxInclusive();
        int count = NumberLengthRule.DEFAULT.uniquesExactly();
        return new Lotto(Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count));
    }

    WinningStats result(WinnerLotto winner) {
        return new WinningStats(this.lottos, winner);
    }

    // TODO 얘도 StringTemplate으로 주입
    // 그리고 이걸 메서드로 직접 반복해서 호출가능한 건 너무 어색함
    // private 만들고 생성자에서 호출을 하든
    // 아니면 ~개를 구매했습니다는 따로 빼서 출력하고
    // 현재 가진 로또를 각 줄에 표현하는 것만 하든
    public void printLotto(Console console) {
        console.printLine(String.format("%d개를 구매했습니다.", lottos.size()));
        this.lottos.forEach(console::printLine);
    }

}
