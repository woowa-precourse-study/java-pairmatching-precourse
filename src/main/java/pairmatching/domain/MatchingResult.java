package pairmatching.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;
import pairmatching.constant.Course;
import pairmatching.constant.Level;
import pairmatching.constant.Mission;

public class MatchingResult {

    private EnumMap<Course, EnumMap<Level, EnumMap<Mission, List<Set<Crew>>>>> matchingResults;

    public static MatchingResult newInstance() {
        return new MatchingResult();
    }

    public void init() {
        matchingResults = new EnumMap<>(Course.class);
        for (Course course : Course.values()) {
            matchingResults.put(course, new EnumMap<>(Level.class));
            for (Level level : Level.values()) {
                matchingResults.get(course).put(level, new EnumMap<>(Mission.class));
                for (Mission mission : Mission.values()) {
                    matchingResults.get(course).get(level).put(mission, new ArrayList<>());
                }
            }
        }
    }

    public boolean isExist(Course course, Level level, Mission mission) {
        return !matchingResults.get(course).get(level).get(mission).isEmpty();
    }
}
