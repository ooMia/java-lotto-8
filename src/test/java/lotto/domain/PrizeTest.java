package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PrizeTest {

    @Nested
    class PrizeOfCases {

        @Test
        void testToString_1stPrize() {
            var prizeWithBonus = Prize.of(6, true);
            assertEquals(Prize.MATCH_SIX, prizeWithBonus);

            var prize = Prize.of(6, false);
            assertEquals(Prize.MATCH_SIX, prize);
        }

        @Test
        void testToString_2ndPrize() {
            var prizeWithBonus = Prize.of(5, true);
            assertEquals(Prize.MATCH_FIVE_WITH_BONUS, prizeWithBonus);
        }

        @Test
        void testToString_3rdPrize() {
            var prize = Prize.of(5, false);
            assertEquals(Prize.MATCH_FIVE_WITHOUT_BONUS, prize);
        }

        @Test
        void testToString_4thPrize() {
            var prizeWithBonus = Prize.of(4, true);
            assertEquals(Prize.MATCH_FOUR, prizeWithBonus);

            var prize = Prize.of(4, false);
            assertEquals(Prize.MATCH_FOUR, prize);
        }

        @Test
        void testToString_5thPrize() {
            var prizeWithBonus = Prize.of(3, true);
            assertEquals(Prize.MATCH_THREE, prizeWithBonus);

            var prize = Prize.of(3, false);
            assertEquals(Prize.MATCH_THREE, prize);
        }

        @ParameterizedTest
        @ValueSource(ints = { 0, 1, 2 })
        void testToString_noPrize(int matches) {
            var prizeWithBonus = Prize.of(matches, true);
            assertEquals(Prize.MATCH_NONE, prizeWithBonus);

            var prize = Prize.of(matches, false);
            assertEquals(Prize.MATCH_NONE, prize);
        }

        @ParameterizedTest
        @ValueSource(ints = { -1, 7 })
        void testToString_exception(int matches) {
            assertThatThrownBy(() -> Prize.of(matches, true))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageStartingWith("[ERROR]")
                    .hasMessageContaining("NUMBER_OUT_OF_RANGE");

            assertThatThrownBy(() -> Prize.of(matches, false))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageStartingWith("[ERROR]")
                    .hasMessageContaining("NUMBER_OUT_OF_RANGE");
        }

    }

}
