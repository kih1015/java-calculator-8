package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperandList {

    private final List<Operand> operandList = new ArrayList<>();

    public OperandList(String expression, String delimiterRegex) {
        String[] tokens = expression.split(delimiterRegex);
        Arrays.stream(tokens).forEach(token -> operandList.add(new Operand(token)));
    }

    public int sum() {
        return operandList.stream()
                .mapToInt(Operand::getOperand)
                .sum();
    }
}
