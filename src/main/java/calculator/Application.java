package calculator;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        String input = ConsoleView.readString();
        List<Delimiter> defaultDelimiters = List.of(new Delimiter(','), new Delimiter(':'));
        AdditionExpression additionExpression = new AdditionExpression(input, defaultDelimiters);
        int result = additionExpression.sum();
        ConsoleView.printResult(result);
    }
}
