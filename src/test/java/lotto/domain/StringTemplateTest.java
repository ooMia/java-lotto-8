package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StringTemplateTest {
    @Test
    void testStatProfitRate_반올림_성공() {
        double rate = 0.25;
        String expected = "0.3";
        String actual = StringTemplate.statProfitRate(rate);
        assertThat(actual).contains(expected);
    }

    @Test
    void testStatProfitRate_반올림_내림_성공() {
        double rate = 0.24;
        String expected = "0.2";
        String actual = StringTemplate.statProfitRate(rate);
        assertThat(actual).contains(expected);
    }

    @Test
    void testStatProfitRate_ZERO() {
        double rate = 0.0;
        String expected = "0.0";
        String actual = StringTemplate.statProfitRate(rate);
        assertThat(actual).contains(expected);
    }
}
