package pairmatching.domain.vo;

import java.util.Objects;

public class Crew {
    private final Course course;
    private final String name;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Crew)) {
            return false;
        }

        Crew crew = (Crew) o;
        return course == crew.course && Objects.equals(name, crew.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(course);
        result = 31 * result + Objects.hashCode(name);
        return result;
    }
}
