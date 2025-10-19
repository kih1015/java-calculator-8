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

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        input = Console.readLine();

        if (input.startsWith(commandString)) {
            String[] tempTokens = input.split(delimitersString, 2);
            String header = tempTokens[0];
            char customDelimiter = header.charAt(commandString.length());
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
                .map(Integer::parseInt)
                .reduce(Integer::sum)
                .ifPresent(result -> System.out.println("결과 : " + result));
    }
}
