package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}
