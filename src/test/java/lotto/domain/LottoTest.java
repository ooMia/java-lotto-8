package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import lotto.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        TestUtil.GLOBAL.assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .hasMessageContaining("NUMBERS_LENGTH_NOT_SIX");
    }

    @Test
    void 로또_번호의_개수가_6개보다_부족하면_예외가_발생한다() {
        TestUtil.GLOBAL.assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .hasMessageContaining("NUMBERS_LENGTH_NOT_SIX");

        TestUtil.GLOBAL.assertThatThrownBy(() -> new Lotto(List.of()))
                .hasMessageContaining("NUMBERS_LENGTH_NOT_SIX");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        TestUtil.GLOBAL.assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .hasMessageContaining("DUPLICATE_NUMBER");
    }

    @Test
    void 로또_번호_범위에_벗어나면_예외가_발생한다() {
        TestUtil.GLOBAL.assertThatThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 5)))
                .hasMessageContaining("NUMBER_OUT_OF_RANGE");

        TestUtil.GLOBAL.assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .hasMessageContaining("NUMBER_OUT_OF_RANGE");
    }

    @Test
    void testToString() {
        Lotto purchase = new Lotto(List.of(8, 21, 23, 41, 42, 43));

        String expected = "[8, 21, 23, 41, 42, 43]";
        String actual = purchase.toString();

        assertEquals(expected, actual);
    }
}
