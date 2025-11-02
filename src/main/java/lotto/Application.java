package lotto;

import lotto.util.Global;

// TODO 흠 뭔가 Controller라는게 와닿지 않아
// 만약에 그렇게 이름 붙일거면 도메인과 관계없이 무엇을 수행할 수 있는 주체가 되어야 할 것 같은데
// 현재 메서드에는 굉장히 많은 강결합이 느껴져
public class Application {
    public static void main(String[] args) {

        // TODO 여기에 도메인을 바로 호출하는 것도 좋아
        // InputConsole 같은 역할을 가진 무언가를 활용해서
        // 사용자의 입력을 정돈된 형태로 받고
        // 그걸 도메인으로 전달해서 검증하는 것까지를 하나의 사이클로 갖는 무언가를 정의해보면 어떨까?
        // 근데 이걸 하려면 DTO를 써야할 것 같아

        var history = new Controller(
                Global.CONSOLE,
                Global.EXCEPTION_HANDLER,
                Global.TOKENIZER);
        history.buyLottos();
        history.fetchWinner();
        history.printStats();
    }
}
