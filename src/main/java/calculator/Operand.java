package calculator;

public record Operand(int operand) {

    public Operand {
        validatePositive(operand);
    }

    private void validatePositive(int operand) {
        if (operand <= 0) {
            throw new IllegalArgumentException("양수만 가능합니다.");
        }
    }
}
