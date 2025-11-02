package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import lotto.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class WinnerLottoTest {

    private List<Integer> baseLottoNumbers;
    private Lotto baseLotto;

    @BeforeEach
    void setUp() {
        this.baseLottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        this.baseLotto = new Lotto(baseLottoNumbers);
    }

    @Test
    void 보너스_번호_범위에_벗어나면_예외가_발생한다() {
        TestUtil.GLOBAL.assertThatThrownBy(() -> new WinnerLotto(baseLotto, 0))
                .hasMessageContaining("NUMBER_OUT_OF_RANGE");

        TestUtil.GLOBAL.assertThatThrownBy(() -> new WinnerLotto(baseLotto, 46))
                .hasMessageContaining("NUMBER_OUT_OF_RANGE");
    }

    @Test
    void 보너스_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        var firstNumber = baseLottoNumbers.getFirst();
        TestUtil.GLOBAL.assertThatThrownBy(() -> new WinnerLotto(baseLotto, firstNumber))
                .hasMessageContaining("DUPLICATE_NUMBER");
    }

    @Test
    void 로또_2등_당첨_경우() {
        Lotto purchase = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinnerLotto winner = new WinnerLotto(new Lotto(List.of(1, 2, 3, 4, 5, 45)), 6);

        Prize expected = Prize.MATCH_FIVE_WITH_BONUS;
        Prize actual = winner.toPrize(purchase);

        assertEquals(expected, actual);
    }

    @Test
    void 로또_3개_당첨_경우() {
        Lotto purchase = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinnerLotto winner = new WinnerLotto(new Lotto(List.of(1, 2, 3, 43, 44, 45)), 6);

        Prize expected = Prize.MATCH_THREE;
        Prize actual = winner.toPrize(purchase);

        assertEquals(expected, actual);
    }

    @Test
    void 로또_미당첨_경우() {
        Lotto purchase = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinnerLotto winner = new WinnerLotto(new Lotto(List.of(1, 2, 42, 43, 44, 45)), 6);

        Prize expected = Prize.MATCH_NONE;
        Prize actual = winner.toPrize(purchase);

        assertEquals(expected, actual);
    }

}
