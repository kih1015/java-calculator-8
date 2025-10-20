package calculator;

import java.util.List;

public class AdditionExpression {

    private static final String HEADER_START_STRING = "//";
    private static final String HEADER_END_STRING = "\\n";

    private final DelimiterList delimiterList;
    private final OperandList operandList;

    public AdditionExpression(String rawCommand, List<Delimiter> defaultDelimiters) {
        this.delimiterList = new DelimiterList(defaultDelimiters);

        if (hasCustomDelimiter(rawCommand)) {
            char customDelimiter = extractCustomDelimiter(rawCommand);
            this.delimiterList.add(customDelimiter);
        }

        String expression = extractExpression(rawCommand);
        this.operandList = new OperandList(expression, delimiterList.toRegex());
    }

    public int sum() {
        return operandList.sum();
    }

    private boolean hasCustomDelimiter(String rawCommand) {
        String header = rawCommand.substring(0, 5);
        return header.startsWith(HEADER_START_STRING) && header.endsWith(HEADER_END_STRING);
    }

    private char extractCustomDelimiter(String rawCommand) {
        return rawCommand.charAt(2);
    }

    private String extractExpression(String rawCommand) {
        if (!hasCustomDelimiter(rawCommand)) {
            return rawCommand;
        }
        return rawCommand.substring(5);
    }
}
