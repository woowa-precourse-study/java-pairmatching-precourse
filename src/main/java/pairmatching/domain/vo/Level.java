package pairmatching.domain.vo;

import java.util.Arrays;
import pairmatching.exception.ErrorCode;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private String name;

    Level(String name) {
        this.name = name;
    }

    public static Level of(String levelValue) {
        return Arrays.stream(values())
                .filter(level -> level.name.equals(levelValue))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NOT_VALID_LEVEL.getMessage()));
    }
}
