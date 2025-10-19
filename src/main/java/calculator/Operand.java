package calculator;

public class Operand {

    private final int operand;

    public Operand(String operand) {
        this.operand = parseToInteger(operand);
        validatePositive(this.operand);
    }

    public int getOperand() {
        return operand;
    }

    private void validatePositive(int operand) {
        if (operand <= 0) {
            throw new IllegalArgumentException("양수만 가능합니다.");
        }
    }

    private int parseToInteger(String operand) {
        try {
            return Integer.parseInt(operand);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 올바르지 않습니다.");
        }
    }
}
