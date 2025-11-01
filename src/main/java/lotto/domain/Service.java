package lotto.domain;

import java.util.List;

public class Service {
    public static final int LOTTO_PRICE = 1_000;

    private static final Vendor vendor = new Vendor(LOTTO_PRICE);

    public List<Lotto> buyLottos(int money) {
        return vendor.buyLotto(money);
    }

    // TODO 이름 재명명: 그냥 로또를 발급하는 식으로
    public Lotto prepareWinnerLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public WinnerLotto completeWinnerLotto(Lotto lotto, int number) {
        return new WinnerLotto(lotto, number);
    }

    public String toStatistics(List<Lotto> lottos, WinnerLotto winner) {
        return new WinningStats(lottos, winner).toString();
    }
}
