package pairmatching.domain;

public record Options (Level level,Course course,String mission){

    public Course getCourse(){
        return course;
    }
}

