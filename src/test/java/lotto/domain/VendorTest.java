package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import lotto.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VendorTest {

    private static final int LOTTO_PRICE = 1_000;

    @ParameterizedTest
    @ValueSource(ints = {LOTTO_PRICE + 1, 1999, 2001})
    void 단위_금액_아니면_예외(int money) {
        TestUtil.GLOBAL.assertThatThrownBy(() -> Vendor.buyLotto(money))
                .hasMessageContaining("MOD_PRICE_NOT_ZERO");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, 999})
    void 금액_없으면_예외(int money) {
        TestUtil.GLOBAL.assertThatThrownBy(() -> Vendor.buyLotto(money))
                .hasMessageContaining("NOT_ENOUGH_MONEY");
    }

    @ParameterizedTest
    @ValueSource(ints = {LOTTO_PRICE, 10000, 100000})
    void 단위_금액이면_성공(int money) {
        assertDoesNotThrow(() -> Vendor.buyLotto(money));
    }

    @Test
    void 금액이_INTEGER_표현을_넘어가면_실패() {
        long divPriceExceedInteger = (1L + Integer.MAX_VALUE) * LOTTO_PRICE;
        TestUtil.GLOBAL.assertThatThrownBy(() -> Vendor.buyLotto(divPriceExceedInteger))
                .hasMessageContaining("REQUEST_EXCEED_MEMORY_LIMITATION");
    }

    @Test
    void 금액으로_메모리_한계를_초과하면_실패() {
        long divPriceExactMaximum = (long) Integer.MAX_VALUE * LOTTO_PRICE;
        TestUtil.GLOBAL.assertThatThrownBy(() -> Vendor.buyLotto(divPriceExactMaximum))
                .hasMessageContaining("REQUEST_EXCEED_MEMORY_LIMITATION");
    }
}
