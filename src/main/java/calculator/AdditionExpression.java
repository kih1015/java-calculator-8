package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdditionExpression {

    private final List<Delimiter> delimiterList = new ArrayList<>();
    private final List<Operand> operandList = new ArrayList<>();

    public AdditionExpression(String rawCommand, Character[] defaultDelimiters) {
        Arrays.stream(defaultDelimiters).forEach(delimiter -> delimiterList.add(new Delimiter(delimiter)));
        Parser parser = new Parser(rawCommand);
        parser.getCustomDelimiter()
                .ifPresent(character -> delimiterList.add(new Delimiter(character)));
        parseExpression(parser.getExpression());
    }

    public int sum() {
        return operandList.stream()
                .mapToInt(Operand::getOperand)
                .sum();
    }

    private void parseExpression(String expression) {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        delimiterList.forEach(delimiter -> builder.append(delimiter.getDelimiter()));
        builder.append(']');
        String regex = builder.toString();

        String[] tokens = expression.split(regex);
        Arrays.stream(tokens).forEach(token -> {
            operandList.add(new Operand(token));
        });
    }
}