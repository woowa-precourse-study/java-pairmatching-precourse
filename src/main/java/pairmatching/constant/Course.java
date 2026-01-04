package pairmatching.constant;

import java.util.Arrays;

public enum Course {

    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public static Course from(String courseName) {
        return Arrays.stream(values())
                .filter(course -> course.name.equals(courseName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_COURSE.getErrorMessage()));
    }
}
