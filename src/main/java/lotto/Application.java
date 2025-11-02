package lotto;

import lotto.util.Global;

public class Application {
    public static void main(String[] args) {
        new Scenario(Global.INSTANCE).run();
    }
}
