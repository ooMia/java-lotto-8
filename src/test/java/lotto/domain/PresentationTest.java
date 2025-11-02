package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PresentationTest {
    @Test
    void testStatProfitRate_반올림_성공() {
        double rate = 0.25;
        String expected = "0.3";
        String actual = Presentation.instance.statProfitRate(rate);
        assertThat(actual).contains(expected);
    }

    @Test
    void testStatProfitRate_반올림_내림_성공() {
        double rate = 0.24;
        String expected = "0.2";
        String actual = Presentation.instance.statProfitRate(rate);
        assertThat(actual).contains(expected);
    }

    @Test
    void testStatProfitRate_ZERO() {
        double rate = 0.0;
        String expected = "0.0";
        String actual = Presentation.instance.statProfitRate(rate);
        assertThat(actual).contains(expected);
    }
}
