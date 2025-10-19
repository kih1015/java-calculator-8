package calculator;

public class Application {

    public static void main(String[] args) {
        String input = ConsoleView.readString();
        Character[] defaultDelimiters = {',', ':'};
        AdditionExpression additionExpression = new AdditionExpression(input, defaultDelimiters);
        int result = additionExpression.sum();
        ConsoleView.printResult(result);
    }
}
