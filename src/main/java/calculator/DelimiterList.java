package calculator;

import java.util.ArrayList;
import java.util.List;

public class DelimiterList {

    private final List<Delimiter> delimiterList;

    public DelimiterList(List<Delimiter> defaultDelimiters) {
        delimiterList = new ArrayList<>(defaultDelimiters);
    }

    public void add(Character delimiter) {
        if (delimiterList.contains(new Delimiter(delimiter))) {
            throw new IllegalArgumentException("구분자가 이미 존재합니다.");
        }
        delimiterList.add(new Delimiter(delimiter));
    }

    public String toRegex() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        delimiterList.forEach(delimiter -> builder.append(delimiter.delimiter()));
        builder.append(']');
        return builder.toString();
    }
}
