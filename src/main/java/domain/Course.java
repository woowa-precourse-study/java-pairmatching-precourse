package domain;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static Course of(String name){
        for (Course course:Course.values()){
            if (course.name.equals(name)){
                return course;
            }
        }
        throw new IllegalArgumentException("[ERROR] 해당 과정이 존재하지 않습니다.");
    }

}