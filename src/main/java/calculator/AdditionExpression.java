package calculator;

public class AdditionExpression {

    private static final String HEADER_START_STRING = "//";
    private static final String HEADER_END_STRING = "\\\\n";

    private final DelimiterList delimiterList;
    private final OperandList operandList;

    public AdditionExpression(String rawCommand, Character[] defaultDelimiters) {
        this.delimiterList = new DelimiterList(defaultDelimiters);
        String expression = parse(rawCommand);
        this.operandList = new OperandList(expression, delimiterList.toRegex());
    }

    public int sum() {
        return operandList.sum();
    }

    private String parse(String rawCommand) {
        if (!rawCommand.startsWith(HEADER_START_STRING)) {
            return rawCommand;
        }

        String[] tempTokens = rawCommand.split(HEADER_END_STRING, 2);
        validateInputString(tempTokens);

        String header = tempTokens[0];
        delimiterList.add(extractCustomDelimiter(header));
        return tempTokens[1];
    }

    private void validateInputString(String[] tempTokens) {
        if (tempTokens.length != 2) {
            throw new IllegalArgumentException("올바르지 않은 입력 형식입니다.");
        }
    }

    private char extractCustomDelimiter(String header) {
        int customDelimiterLength = 1;
        if (header.length() != HEADER_START_STRING.length() + customDelimiterLength) {
            throw new IllegalArgumentException("커스텀 구분자는 유일합니다.");
        }
        return header.charAt(HEADER_START_STRING.length());
    }
}
