package pairmatching.domain;

import java.util.Objects;

public class Choice {
    private final Course course;
    private final Level level;
    private final String mission;

    public Choice(Course course, Level level, String mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public String getMission() {
        return mission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Choice choice = (Choice) o;
        return course == choice.course && level == choice.level && Objects.equals(mission, choice.mission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level, mission);
    }
}
