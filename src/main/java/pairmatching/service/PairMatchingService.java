package pairmatching.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import pairmatching.constant.Course;
import pairmatching.constant.Level;
import pairmatching.constant.Mission;
import pairmatching.domain.Crew;
import pairmatching.domain.Crews;
import pairmatching.domain.MatchingResult;

public class PairMatchingService {

    private Crews crews;
    private MatchingResult matchingResult;

    public void registerFileInfo(Course course, List<String> names) {
        crews = Crews.getInstance();

        for (String name : names) {
            crews.addCrew(course, name);
        }
    }

    public void init() {
        matchingResult = MatchingResult.newInstance();
        matchingResult.init();
    }

    public boolean MatchingResultIsExist(Course course, Level level, Mission mission) {
        return matchingResult.isExist(course, level, mission);
    }

    public List<Set<Crew>> generateMatching(Course course, Level level, Mission mission) {
        List<Set<Crew>> matching = matchingResult.generateMatching(crews, course);
        if (matchingResult.isPossible(matching, course, level)) {
            matchingResult.addResult(matching, course, level, mission);
            return matching;
        }

        return new ArrayList<>();
    }

    public void removeRecord(Course course, Level level, Mission mission) {
        matchingResult.remove(course, level, mission);
    }
}
