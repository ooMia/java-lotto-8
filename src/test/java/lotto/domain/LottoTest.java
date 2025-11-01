package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]")
                .hasMessageContaining("NUMBERS_LENGTH_NOT_SIX");
    }

    @Test
    void 로또_번호의_개수가_6개_이하이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]")
                .hasMessageContaining("NUMBERS_LENGTH_NOT_SIX");

        assertThatThrownBy(() -> new Lotto(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]")
                .hasMessageContaining("NUMBERS_LENGTH_NOT_SIX");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]")
                .hasMessageContaining("DUPLITCATE_NUMBER");
    }

    @Test
    void 로또_번호_범위에_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]")
                .hasMessageContaining("NUMBER_OUT_OF_RANGE");

        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]")
                .hasMessageContaining("NUMBER_OUT_OF_RANGE");
    }

    // TODO WinnerLotto 쪽으로 마이그레이션
    @Test
    void 로또_2등_당첨_경우() {
        Lotto purchase = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinnerLotto winner = new WinnerLotto(new Lotto(List.of(1, 2, 3, 4, 5, 45)), 6);

        Prize expected = Prize.MATCH_FIVE_WITH_BONUS;
        Prize actual = purchase.toPrize(winner);

        assertEquals(expected, actual);
    }

    // TODO WinnerLotto 쪽으로 마이그레이션
    @Test
    void 로또_3개_당첨_경우() {
        Lotto purchase = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinnerLotto winner = new WinnerLotto(new Lotto(List.of(1, 2, 3, 43, 44, 45)), 6);

        Prize expected = Prize.MATCH_THREE;
        Prize actual = purchase.toPrize(winner);

        assertEquals(expected, actual);
    }

    // TODO WinnerLotto 쪽으로 마이그레이션
    @Test
    void 로또_미당첨_경우() {
        Lotto purchase = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinnerLotto winner = new WinnerLotto(new Lotto(List.of(1, 2, 42, 43, 44, 45)), 6);

        Prize expected = Prize.MATCH_NONE;
        Prize actual = purchase.toPrize(winner);

        assertEquals(expected, actual);
    }

    @Test
    void testToString() {
        Lotto purchase = new Lotto(List.of(8, 21, 23, 41, 42, 43));
        String expected = "[8, 21, 23, 41, 42, 43]";
        String actual = purchase.toString();
        assertEquals(expected, actual);
    }
}
