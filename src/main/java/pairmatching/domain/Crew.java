package pairmatching.domain;

import java.util.Objects;
import pairmatching.constant.Course;

public class Crew {

    private final Course course;
    private final String name;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public static Crew of(Course course, String name) {
        return new Crew(course, name);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Crew crew = (Crew) object;
        return course == crew.course && Objects.equals(name, crew.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, name);
    }
}
