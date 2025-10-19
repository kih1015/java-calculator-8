package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperandList {

    private final List<Operand> operandList = new ArrayList<>();

    public OperandList(String expression, List<Delimiter> delimiters) {
        String regex = getDelimitersRegex(delimiters);
        String[] tokens = expression.split(regex);
        Arrays.stream(tokens).forEach(token -> operandList.add(new Operand(token)));
    }

    public int sum() {
        return operandList.stream()
                .mapToInt(Operand::getOperand)
                .sum();
    }

    private String getDelimitersRegex(List<Delimiter> delimiters) {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        delimiters.forEach(delimiter -> builder.append(delimiter.getDelimiter()));
        builder.append(']');
        return builder.toString();
    }
}
