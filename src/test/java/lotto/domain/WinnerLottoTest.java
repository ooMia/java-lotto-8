package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class WinnerLottoTest {

    @Test
    void 보너스_번호_범위에_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinnerLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]")
                .hasMessageContaining("NUMBER_OUT_OF_RANGE");

        assertThatThrownBy(() -> new WinnerLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]")
                .hasMessageContaining("NUMBER_OUT_OF_RANGE");
    }

    @Test
    void 보너스_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinnerLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]")
                .hasMessageContaining("DUPLITCATE_NUMBER");
    }

    // TODO WinnerLotto 쪽으로 마이그레이션
    @Test
    void 로또_2등_당첨_경우() {
        Lotto purchase = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinnerLotto winner = new WinnerLotto(new Lotto(List.of(1, 2, 3, 4, 5, 45)), 6);

        Prize expected = Prize.MATCH_FIVE_WITH_BONUS;
        Prize actual = winner.toPrize(purchase);

        assertEquals(expected, actual);
    }

    // TODO WinnerLotto 쪽으로 마이그레이션
    @Test
    void 로또_3개_당첨_경우() {
        Lotto purchase = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinnerLotto winner = new WinnerLotto(new Lotto(List.of(1, 2, 3, 43, 44, 45)), 6);

        Prize expected = Prize.MATCH_THREE;
        Prize actual = winner.toPrize(purchase);

        assertEquals(expected, actual);
    }

    // TODO WinnerLotto 쪽으로 마이그레이션
    @Test
    void 로또_미당첨_경우() {
        Lotto purchase = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinnerLotto winner = new WinnerLotto(new Lotto(List.of(1, 2, 42, 43, 44, 45)), 6);

        Prize expected = Prize.MATCH_NONE;
        Prize actual = winner.toPrize(purchase);

        assertEquals(expected, actual);
    }

}
