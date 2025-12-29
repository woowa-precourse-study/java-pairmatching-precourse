package pairmatching.domain.vo;

import static pairmatching.exception.ErrorCode.NOT_VALID_COURSE;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public static Course of(String value) {
        return Arrays.stream(values())
                .filter(course -> course.name.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_VALID_COURSE.getMessage()));
    }
}
