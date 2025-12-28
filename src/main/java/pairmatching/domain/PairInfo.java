package pairmatching.domain;

import java.util.List;

public class PairInfo {
    private Course course;
    private Level level;
    private String mission;
    private List<List<String>> pairList;

    public PairInfo(Course course, Level level, String mission, List<List<String>> pairList) {
        this.course = course;
        this.level = level;
        this.mission = mission;
        this.pairList = pairList;
    }
}
