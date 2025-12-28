package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class MatchingMachine {
    private final Options options;
    private final List<List<String>> crews;

    public MatchingMachine(String levelInput,String course,String mission, List<List<String>> crews) {
        Level level = Level.fromLevel(levelInput);
        level.validateMission(mission);
        this.options=new Options(level, Course.fromName(course), mission);
        this.crews=crews;
    }


}
