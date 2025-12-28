package pairmatching.domain;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static Course getCourse(String name) {
        for (Course course : values()) {
            if (course.name.equals(name)) {
                return course;

            }
        }
        throw new IllegalArgumentException("잘못된 Course");
    }

    // 추가 기능 구현
}