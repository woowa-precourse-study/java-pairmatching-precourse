package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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

    public List<Set<Crew>> generateMatching(Crews crews, Course course) {
        List<String> preCrews = crews.getCrews().get(course).stream().map(Crew::getName).collect(Collectors.toList());
        List<String> shuffleCrews = Randoms.shuffle(preCrews);

        List<Set<Crew>> matching = new ArrayList<>();

        int index = 0;
        while (index < shuffleCrews.size()) {
            Crew firstCrew = crews.getCrew(shuffleCrews.get(index++));
            Crew secondCrew = crews.getCrew(shuffleCrews.get(index++));
            Crew thirdCrew;

            if (index == shuffleCrews.size() - 1) {
                thirdCrew = crews.getCrew(shuffleCrews.get(index++));
                matching.add(new HashSet<>(Arrays.asList(firstCrew, secondCrew, thirdCrew)));
                continue;
            }

            matching.add(new HashSet<>(Arrays.asList(firstCrew, secondCrew)));
        }

        return matching;
    }

    public void remove(Course course, Level level, Mission mission) {
        matchingResults.get(course).get(level).get(mission).clear();
    }

    public boolean isPossible(List<Set<Crew>> matching, Course course, Level level) {
        EnumMap<Mission, List<Set<Crew>>> matchingHistory = matchingResults.get(course).get(level);
        for (Set<Crew> crews : matching) {
            for (Crew crew : crews) {
                HashSet<Crew> otherCrews = new HashSet<>(crews);
                otherCrews.remove(crew);
                for (Crew otherCrew : otherCrews) {
                    HashSet<Crew> pair = new HashSet<>(Arrays.asList(crew, otherCrew));
                    for (List<Set<Crew>> histories : matchingHistory.values()) {
                        if (!histories.isEmpty()) {
                            for (Set<Crew> history : histories) {
                                if (history.containsAll(pair)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public void addResult(List<Set<Crew>> matching, Course course, Level level, Mission mission) {
        matchingResults.get(course).get(level).put(mission, matching);
    }

    public List<Set<Crew>> getMatching(Course course, Level level, Mission mission) {
        return matchingResults.get(course).get(level).get(mission);
    }
}
