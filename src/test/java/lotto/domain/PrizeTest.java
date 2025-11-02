package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lotto.TestUtil;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PrizeTest {

    @Nested
    class PrizeOfCases {

        @Test
        void testToString_1stPrize() {
            var prizeWithBonus = Prize.ofResult(6, true);
            assertEquals(Prize.MATCH_SIX, prizeWithBonus);

            var prize = Prize.ofResult(6, false);
            assertEquals(Prize.MATCH_SIX, prize);
        }

        @Test
        void testToString_2ndPrize() {
            var prizeWithBonus = Prize.ofResult(5, true);
            assertEquals(Prize.MATCH_FIVE_WITH_BONUS, prizeWithBonus);
        }

        @Test
        void testToString_3rdPrize() {
            var prize = Prize.ofResult(5, false);
            assertEquals(Prize.MATCH_FIVE_WITHOUT_BONUS, prize);
        }

        @Test
        void testToString_4thPrize() {
            var prizeWithBonus = Prize.ofResult(4, true);
            assertEquals(Prize.MATCH_FOUR, prizeWithBonus);

            var prize = Prize.ofResult(4, false);
            assertEquals(Prize.MATCH_FOUR, prize);
        }

        @Test
        void testToString_5thPrize() {
            var prizeWithBonus = Prize.ofResult(3, true);
            assertEquals(Prize.MATCH_THREE, prizeWithBonus);

            var prize = Prize.ofResult(3, false);
            assertEquals(Prize.MATCH_THREE, prize);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2})
        void testToString_noPrize(int matches) {
            var prizeWithBonus = Prize.ofResult(matches, true);
            assertEquals(Prize.MATCH_NONE, prizeWithBonus);

            var prize = Prize.ofResult(matches, false);
            assertEquals(Prize.MATCH_NONE, prize);
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, 7})
        void testToString_exception(int matches) {
            TestUtil.GLOBAL.assertThatThrownBy(() -> Prize.ofResult(matches, true))
                    .hasMessageContaining("NUMBER_OUT_OF_RANGE");

            TestUtil.GLOBAL.assertThatThrownBy(() -> Prize.ofResult(matches, false))
                    .hasMessageContaining("NUMBER_OUT_OF_RANGE");
        }

    }

}
