package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class PairGroup {
    private final List<Pair> pairs=new ArrayList<>();

    public void add(Pair pair){
        pairs.add(pair);
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public void validateNewMatched(Pair newPair){
        for (Pair pair:pairs){
            pair.validateDifferent(newPair);
        }
    }
}
