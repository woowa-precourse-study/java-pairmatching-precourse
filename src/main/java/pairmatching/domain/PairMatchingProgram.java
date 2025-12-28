package pairmatching.domain;

import java.util.List;
import java.util.Map;

public class PairMatchingProgram {
    private final Map<Course, CrewGroup> group;
    private final PairGenerator generator;
    private MatchingRecords records;

    public PairMatchingProgram(Map<Course, CrewGroup> group, PairGenerator generator, MatchingRecords records) {
        this.group = group;
        this.generator = generator;
        this.records = records;
    }

    public List<Pair> createPairs(MatchingCondition condition) {
        Course course = condition.getCourse();
        CrewGroup crewGroup = group.get(course);

        List<Pair> pairs = crewGroup.generatePairs(generator);
        while (records.checkPairs(condition, pairs)) {
            pairs = crewGroup.generatePairs(generator);
        }

        records = records.savePairs(condition, pairs);

        return pairs;
    }

    public boolean exists(MatchingCondition condition) {
        return records.contains(condition);
    }

    public MatchingRecords getRecords() {
        return records;
    }
}
