package pairmatching.domain;

public enum Course {
    BACKEND("백엔드","backend-crew2.md"),
    FRONTEND("프론트엔드","frontend-crew2.md");

    private String name;
    private String fileName;

    Course(String name, String fileName) {
        this.name = name;
        this.fileName=fileName;
    }

    // 코스가 존재하는지 확인
    public static Course fromName(String name){
        for (Course course : Course.values()) {
            if (course.name.equals(name)) {
                return course;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 카테고리");
    }

    public String getFileName(){
        return fileName;
    }

}