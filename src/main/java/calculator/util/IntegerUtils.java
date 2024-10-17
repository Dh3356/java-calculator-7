package calculator.util;

import java.util.List;

public class IntegerUtils {

    public static int sum(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    public static List<Integer> parsePositiveIntegers(List<String> values) {
        return values.stream().map(value -> {
            int number = value.isEmpty() ? 0 : Integer.parseInt(value);
            validatePositiveInteger(number);
            return number;
        }).toList();
    }

    private static void validatePositiveInteger(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수입니다.");
        }
    }
}
