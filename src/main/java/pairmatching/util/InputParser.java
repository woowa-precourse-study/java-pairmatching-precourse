package pairmatching.util;

import java.util.List;
import pairmatching.exception.ErrorCode;

public class InputParser {
    private static final String DELIMITER = ",";

    public static List<String> parse(String input) {
        String replace = input.replace(" ", "");
        String[] split = replace.split(DELIMITER);

        if (split.length != 3) {
            throw new IllegalArgumentException(ErrorCode.NOT_VALID_COURSE_AND_MISSION.getMessage());
        }
        
        return List.of(split);
    }
}
