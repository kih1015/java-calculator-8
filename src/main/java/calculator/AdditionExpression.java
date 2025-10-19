package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdditionExpression {

    private static final String HEADER_START_STRING = "//";
    private static final String HEADER_END_STRING = "\\\\n";

    private final List<Character> delimiterList = new ArrayList<>();
    private final List<Integer> operandList = new ArrayList<>();

    public AdditionExpression(String rawCommand, Character[] defaultDelimiters) {
        delimiterList.addAll(Arrays.asList(defaultDelimiters));
        String expression = parseRawCommand(rawCommand);
        parseExpression(expression);
    }

    public int sum() {
        return operandList.stream()
                .mapToInt(Integer::intValue)
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

        delimiterList.add(customDelimiter);
        return expression;
    }

    private void parseExpression(String expression) {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        delimiterList.forEach(builder::append);
        builder.append(']');
        String regex = builder.toString();

        String[] tokens = expression.split(regex);
        Arrays.stream(tokens).forEach(token -> {
            operandList.add(parseNumber(token));
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
        if (delimiterList.contains(customDelimiter)) {
            throw new IllegalArgumentException("커스텀 구분자는 기본 구분자일 수 없습니다.");
        }
        if (Character.isDigit(customDelimiter)) {
            throw new IllegalArgumentException("커스텀 구분자는 숫자일 수 없습니다.");
        }
    }

    private int parseNumber(String token) {
        try {
            int parsed = Integer.parseInt(token);
            if (parsed <= 0) {
                throw new IllegalArgumentException("양수만 가능합니다.");
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 올바르지 않습니다.");
        }
    }
}