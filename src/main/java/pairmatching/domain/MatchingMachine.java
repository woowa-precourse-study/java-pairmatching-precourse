package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class MatchingMachine {
    private final Level level;
    private final Course course;
    private final String mission;
    private final List<List<String>> crews;

    public MatchingMachine(Level level,Course course,String mission, List<List<String>> crews) {
        this.level=level;
        this.course=course;
        this.mission=mission;
        this.crews=crews;
    }


}
