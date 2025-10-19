package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DelimiterList {

    private final List<Delimiter> delimiterList = new ArrayList<>();

    public DelimiterList(Character[] defaultDelimiters) {
        Arrays.stream(defaultDelimiters).forEach(delimiter -> delimiterList.add(new Delimiter(delimiter)));
    }

    public void add(Character delimiter) {
        if (delimiterList.contains(new Delimiter(delimiter))) {
            throw new IllegalArgumentException("구분자가 이미 존재합니다.");
        }
        delimiterList.add(new Delimiter(delimiter));
    }

    public List<Delimiter> getDelimiterList() {
        return new ArrayList<>(delimiterList);
    }
}
