package calculator;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Application {

    public static void main(String[] args) {

        Set<String> delimiters = new HashSet<>(Set.of(",", ":"));
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = scanner.nextLine();

        if (input.contains("//") && input.contains("\\n")) {
            if (!input.startsWith("//")) {
                throw new IllegalArgumentException("잘못된 형식입니다.");
            }
            String customDelimiter = input.split("\\\\n")[0].substring("//".length());
            if (customDelimiter.isEmpty()) {
                throw new IllegalArgumentException("커스텀 구분자가 비어있습니다.");
            }
            if (customDelimiter.contains("\\")) {
                throw new IllegalArgumentException("이스케이프 문자가 포함되었습니다.");
            } else if (customDelimiter.matches(".*\\d.*")) {
                throw new IllegalArgumentException("커스텀 구분자엔 숫자가 포함될 수 없습니다.");
            }
            delimiters.add(customDelimiter);
            input = input.split("\\\\n")[1];
        }

        String[] numberTokens = input.split(String.join("|", delimiters));
        int sum = calculator.sum(numberTokens);

        System.out.print("결과 : " + sum);
    }
}
