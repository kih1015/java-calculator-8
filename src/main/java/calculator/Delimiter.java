package calculator;

public record Delimiter(char delimiter) {

    public Delimiter {
        validateNotNumber(delimiter);
    }

    private void validateNotNumber(char delimiter) {
        if (Character.isDigit(delimiter)) {
            throw new IllegalArgumentException("커스텀 구분자는 숫자일 수 없습니다.");
        }
    }
}
