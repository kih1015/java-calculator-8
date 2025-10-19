package calculator;

import java.util.Optional;

public class Parser {

    private static final String HEADER_START_STRING = "//";
    private static final String HEADER_END_STRING = "\\\\n";

    private final Character customDelimiter;
    private final String expression;

    public Parser(String rawCommand) {
        if (!rawCommand.startsWith(HEADER_START_STRING)) {
            expression = rawCommand;
            customDelimiter = null;
            return;
        }

        String[] tempTokens = rawCommand.split(HEADER_END_STRING, 2);
        validateInputString(tempTokens);

        String header = tempTokens[0];
        expression = tempTokens[1];
        customDelimiter = extractCustomDelimiter(header);
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

    public String getExpression() {
        return expression;
    }

    public Optional<Character> getCustomDelimiter() {
        return Optional.ofNullable(customDelimiter);
    }
}
