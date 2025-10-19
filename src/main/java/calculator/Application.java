package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        String input;
        String[] tokens;

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        input = Console.readLine();
        tokens = input.split("[,:]");

        if (input.isEmpty()) {
            System.out.println("결과 : " + 0);
            return;
        }

        Arrays.stream(tokens)
                .map(Integer::parseInt)
                .reduce(Integer::sum)
                .ifPresent(result -> System.out.println("결과 : " + result));
    }
}
