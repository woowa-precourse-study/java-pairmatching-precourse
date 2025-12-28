package pairmatching.util;

import java.util.ArrayList;
import java.util.List;

public final class InputParser {

    private static final String DELIMITER = ",";
    private static final String FIRST_DELIMITER = ",";
    private static final String SECOND_DELIMITER = "-";

    private InputParser() {
    }

    public static List<String> parseContents(String readContents) {
        Validator.validateContentsFormat(readContents);

        List<String> contents = new ArrayList<>();
        for (String s : readContents.split(",")) {
            contents.add(s.trim());
        }
        return contents;
    }
}
