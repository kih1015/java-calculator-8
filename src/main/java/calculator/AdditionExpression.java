package calculator;

public class AdditionExpression {

    private final DelimiterList delimiterList;
    private final OperandList operandList;

    public AdditionExpression(String rawCommand, Character[] defaultDelimiters) {
        this.delimiterList = new DelimiterList(defaultDelimiters);
        Parser parser = new Parser(rawCommand);
        parser.getCustomDelimiter()
                .ifPresent(delimiterList::add);
        this.operandList = new OperandList(parser.getExpression(), delimiterList.getDelimiterList());
    }

    public int sum() {
        return operandList.sum();
    }
}