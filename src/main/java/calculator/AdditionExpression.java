package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdditionExpression {

    private static final String HEADER_START_STRING = "//";
    private static final String HEADER_END_STRING = "\\\\n";

    private final List<Delimiter> delimiterList = new ArrayList<>();
    private final List<Operand> operandList = new ArrayList<>();

    public AdditionExpression(String rawCommand, Character[] defaultDelimiters) {
        Arrays.stream(defaultDelimiters).forEach(delimiter -> delimiterList.add(new Delimiter(delimiter)));
        String expression = parseRawCommand(rawCommand);
        parseExpression(expression);
    }

    public int sum() {
        return operandList.stream()
                .mapToInt(Operand::getOperand)
                .sum();
    }

    private String parseRawCommand(String rawCommand) {
        if (!rawCommand.startsWith(HEADER_START_STRING)) {
            return rawCommand;
        }

        String[] tempTokens = rawCommand.split(HEADER_END_STRING, 2);
        validateInputString(tempTokens);

        String header = tempTokens[0];
        String expression = tempTokens[1];
        char customDelimiter = extractCustomDelimiter(header);
        validateCustomDelimiter(customDelimiter);

        delimiterList.add(new Delimiter(customDelimiter));
        return expression;
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

    private void validateCustomDelimiter(char customDelimiter) {
        if (delimiterList.contains(new Delimiter(customDelimiter))) {
            throw new IllegalArgumentException("커스텀 구분자는 기본 구분자일 수 없습니다.");
        }
    }
}