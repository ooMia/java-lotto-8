package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 단일 문자 구분자 패턴에 따라 문자열을 토큰화하는 유틸리티 클래스
 */
public final class Tokenizer {
    private final Pattern pattern;

    public Tokenizer(char delimiter) {
        this.pattern = Pattern.compile(String.valueOf(delimiter));
    }

    public List<String> split(String input) {
        return split(input, String::intern);
    }

    /**
     * 문자열을 구분자 기준으로 토큰화하고, 변환 함수에 따라 리스트에 담아 반환한다.
     *
     * @param <T>       목표 타입
     * @param input     분리할 문자열
     * @param converter 변환 함수
     * @return 변환된 객체 리스트
     */
    public <T> List<T> split(String input, Function<String, T> converter) {
        validate(input, converter);
        return cleanUp(input)
                .map(converter)
                .toList();
    }

    private Stream<String> cleanUp(String input) {
        String[] tokens = pattern.split(input);
        return Arrays.stream(tokens)
                .map(String::trim)
                .filter(s -> !s.isEmpty());
    }

    private static void validate(String input, Function<String, ?> converter) {
        if (input == null || converter == null) {
            throw new IllegalArgumentException();
        }
    }
}
