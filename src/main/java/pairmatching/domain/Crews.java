package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.constant.Course;

public class Crews {

    private final Map<Course, List<Crew>> crews;

    private Crews() {
        crews = new HashMap<>();
        crews.put(Course.BACKEND, new ArrayList<>());
        crews.put(Course.FRONTEND, new ArrayList<>());
    }

    public static Crews newInstance() {
        return new Crews();
    }

    public void addCrew(Course course, String name) {
        crews.get(course).add(Crew.of(course, name));
    }

    public Map<Course, List<Crew>> getCrews() {
        return crews;
    }
}
