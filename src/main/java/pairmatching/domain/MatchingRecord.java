package pairmatching.domain;

import java.util.List;

public class MatchingRecord {
    private final Course course;
    private final Level level;
    private final String mission;
    private final List<Pair> pairs;

    private MatchingRecord(Course course, Level level, String mission, List<Pair> pairs) {
        this.course = course;
        this.level = level;
        this.mission = mission;
        this.pairs = pairs;
    }

    public boolean equals(Course course, Level level, String mission) {
        return this.course == course && this.level == level && this.mission.equals(mission);
    }

    public static MatchingRecord from(Course course, Level level, String mission, List<Pair> pairs) {
        return new MatchingRecord(course, level, mission, pairs);
    }
}
