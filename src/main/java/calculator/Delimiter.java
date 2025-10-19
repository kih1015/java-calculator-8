package calculator;

public class Delimiter {

    private final char delimiter;

    public Delimiter(char delimiter) {
        validateNotNumber(delimiter);
        this.delimiter = delimiter;
    }

    public char getDelimiter() {
        return delimiter;
    }

    private void validateNotNumber(char delimiter) {
        if (Character.isDigit(delimiter)) {
            throw new IllegalArgumentException("커스텀 구분자는 숫자일 수 없습니다.");
        }
    }
}
