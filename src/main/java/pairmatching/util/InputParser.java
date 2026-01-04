package pairmatching.util;

import java.util.ArrayList;
import java.util.List;

public final class InputParser {

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
