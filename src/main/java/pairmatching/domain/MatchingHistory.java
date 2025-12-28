package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.constant.Course;

public class MatchingHistory {

    private Map<Crew, List<Crew>> histories;

    private MatchingHistory() {
        histories = new HashMap<>();
    }

    public static MatchingHistory newInstance() {
        return new MatchingHistory();
    }

    public void init(Crews crews) {
        List<Crew> backEndCrew = crews.getCrews().get(Course.BACKEND);
        for (Crew crew : backEndCrew) {
            histories.putIfAbsent(crew, new ArrayList<>());
        }
        List<Crew> frontEndCrew = crews.getCrews().get(Course.FRONTEND);
        for (Crew crew : frontEndCrew) {
            histories.putIfAbsent(crew, new ArrayList<>());
        }
    }
}
