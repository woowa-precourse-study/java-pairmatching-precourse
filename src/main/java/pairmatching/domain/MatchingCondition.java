package pairmatching.domain;

import java.util.Objects;
import pairmatching.domain.vo.Course;
import pairmatching.domain.vo.Level;
import pairmatching.domain.vo.Mission;

public class MatchingCondition {
    private final Course course;
    private final Level level;
    private final Mission mission;

    private MatchingCondition(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public static MatchingCondition from(Course course, Level level, Mission mission) {
        return new MatchingCondition(course, level, mission);
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof MatchingCondition)) {
            return false;
        }

        MatchingCondition that = (MatchingCondition) o;
        return course == that.course && level == that.level && Objects.equals(mission, that.mission);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(course);
        result = 31 * result + Objects.hashCode(level);
        result = 31 * result + Objects.hashCode(mission);
        return result;
    }
}
