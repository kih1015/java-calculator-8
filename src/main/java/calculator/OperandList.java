package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperandList {

    private final List<Operand> operandList = new ArrayList<>();

    public OperandList(String expression, String delimiterRegex) {
        if (expression.isEmpty()) {
            return;
        }
        String[] tokens = expression.split(delimiterRegex);
        Arrays.stream(tokens)
                .map(this::parseToInteger)
                .forEach(token -> operandList.add(new Operand(token)));
    }

    public int sum() {
        return operandList.stream()
                .mapToInt(Operand::operand)
                .sum();
    }

    private int parseToInteger(String operand) {
        try {
            return Integer.parseInt(operand);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 올바르지 않습니다.");
        }
    }
}
