package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        String input;
        String[] tokens;
        String delimiters = ",:";
        String body;
        final String commandString = "//";
        final String delimitersString = "\\\\n";
        final int customDelimiterLength = 1;

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        input = Console.readLine();

        if (input.startsWith(commandString)) {
            String[] tempTokens = input.split(delimitersString, 2);
            if (tempTokens.length != 2) {
                throw new IllegalArgumentException("올바르지 않은 입력 형식입니다.");
            }
            String header = tempTokens[0];
            if (header.length() != commandString.length() + customDelimiterLength) {
                throw new IllegalArgumentException("커스텀 구분자는 유일합니다.");
            }
            char customDelimiter = header.charAt(commandString.length());
            if (delimiters.contains(String.valueOf(customDelimiter))) {
                throw new IllegalArgumentException("커스텀 구분자는 기본 구분자일 수 없습니다.");
            }
            if (Character.isDigit(customDelimiter)) {
                throw new IllegalArgumentException("커스텀 구분자는 숫자일 수 없습니다.");
            }
            delimiters += customDelimiter;
            body = tempTokens[1];
        } else {
            body = input;
        }

        if (body.isEmpty()) {
            System.out.println("결과 : " + 0);
            return;
        }

        tokens = body.split("[" + delimiters + "]");
        Arrays.stream(tokens)
                .map(token -> {
                    try {
                        int parsed = Integer.parseInt(token);
                        if (parsed <= 0) {
                            throw new IllegalArgumentException("양수만 가능합니다.");
                        }
                        return parsed;
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("");
                    }
                })
                .reduce(Integer::sum)
                .ifPresent(result -> System.out.println("결과 : " + result));
    }
}
