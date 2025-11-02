package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import lotto.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VendorTest {

    private Vendor vendor;

    @BeforeEach
    void setUp() {
        this.vendor = new Vendor();
    }

    @ParameterizedTest
    @ValueSource(ints = {1001, 1999, 2001})
    void 단위_금액_아니면_예외(int money) {
        TestUtil.GLOBAL.assertThatThrownBy(() -> vendor.buyLotto(money))
                .hasMessageContaining("MOD_PRICE_NOT_ZERO");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, 999})
    void 금액_없으면_예외(int money) {
        TestUtil.GLOBAL.assertThatThrownBy(() -> vendor.buyLotto(money))
                .hasMessageContaining("NOT_ENOUGH_MONEY");
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 10000, 100000})
    void 단위_금액이면_성공(int money) {
        assertDoesNotThrow(() -> vendor.buyLotto(money));
    }

}
