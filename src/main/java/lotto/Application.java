package lotto;

import lotto.domain.LottoManager;
import lotto.util.Global;

public class Application {
    public static void main(String[] args) {
        new Scenario(Global.INSTANCE, new LottoManager()).run();
    }
}
