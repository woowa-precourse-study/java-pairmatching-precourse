package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.constant.Course;
import pairmatching.constant.ErrorMessage;

public class Crews {

    private static Crews CREWS;

    private final Map<Course, List<Crew>> crews;

    private Crews() {
        crews = new HashMap<>();
        crews.put(Course.BACKEND, new ArrayList<>());
        crews.put(Course.FRONTEND, new ArrayList<>());
    }

    public static Crews getInstance() {
        if (CREWS == null) {
            CREWS = new Crews();
        }
        return CREWS;
    }

    public void addCrew(Course course, String name) {
        crews.get(course).add(Crew.of(course, name));
    }

    public Map<Course, List<Crew>> getCrews() {
        return crews;
    }

    public Crew getCrew(String name) {
        for (List<Crew> crewsOnCourse : crews.values()) {
            for (Crew crew : crewsOnCourse) {
                if (crew.getName().equals(name)) {
                    return crew;
                }
            }
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getErrorMessage());
    }
}
