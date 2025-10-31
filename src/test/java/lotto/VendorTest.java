package lotto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VendorTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 1, 999, 1001})
    void 단위_금액_아니면_예외(int money) {
        assertThrows(IllegalArgumentException.class, () -> new Vendor(money));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 10000, 100000})
    void 단위_금액이면_성공(int money) {
        assertDoesNotThrow(() -> new Vendor(money));
    }

}
