package calculator;

import java.util.*;

public class Application {

    public static void main(String[] args) {

        Set<String> delimiters = new HashSet<>(Set.of(",", ":"));
        List<Integer> numbers = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = scanner.nextLine();

        if (input.contains("//") && input.contains("\\n")) {
            if (!input.startsWith("//")) {
                throw new IllegalArgumentException("잘못된 형식입니다.");
            }
            String customDelimiter = input.split("\\\\n")[0].split("//")[1];
            if (customDelimiter.contains("\\")) {
                throw new IllegalArgumentException("이스케이프 문자가 포함되었습니다.");
            } else if (customDelimiter.matches(".*\\d.*")) {
                throw new IllegalArgumentException("커스텀 구분자엔 숫자가 포함될 수 없습니다.");
            }
            delimiters.add(customDelimiter);
            input = input.split("\\\\n")[1];
        }

        String[] numberTokens = input.split(String.join("|", delimiters));

        for (String numberToken : numberTokens) {
            if (numberToken.isEmpty()) {
                numbers.add(0);
                continue;
            }

            try {
                int number = Integer.parseInt(numberToken);
                if (number < 0) {
                    throw new IllegalArgumentException("음수입니다.");
                }
                numbers.add(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("구분자가 아닌 문자가 포함되어 있습니다.");
            }
        }

        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
    }
}
