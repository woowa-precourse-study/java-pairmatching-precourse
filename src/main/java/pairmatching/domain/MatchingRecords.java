package pairmatching.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchingRecords {
    private final Map<MatchingCondition, List<Pair>> records;

    public MatchingRecords(Map<MatchingCondition, List<Pair>> records) {
        this.records = records;
    }

    public boolean contains(MatchingCondition condition) {
        List<Pair> pairs = records.get(condition);
        return pairs != null;
    }

    public boolean checkPairs(MatchingCondition condition, List<Pair> pairs) {
        List<Pair> comparePair = records.get(condition);
        if (comparePair == null) {
            return false;
        }

        for (Pair pair : pairs) {
            if (comparePair.contains(pair)) {
                return true;
            }
        }

        return false;
    }

    public MatchingRecords savePairs(MatchingCondition condition, List<Pair> pairs) {
        Map<MatchingCondition, List<Pair>> newRecords = new HashMap<>(records);

        newRecords.put(condition, pairs);

        return new MatchingRecords(newRecords);
    }
}
