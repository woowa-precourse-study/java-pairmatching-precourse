package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pair {

    public final List<Crew> pair;

    private Pair(List<Crew> pair) {
        this.pair = pair;
    }

    public static Pair of(Crew... crews) {
        return new Pair(Arrays.asList(crews));
    }

    public List<Pair> getPairs() {
        List<Pair> pairs = new ArrayList<>();
        if (pair.size() == 2) {
            pairs.add(this);
            return pairs;
        }

        pairs.add(Pair.of(pair.get(0), pair.get(1)));
        pairs.add(Pair.of(pair.get(0), pair.get(2)));
        pairs.add(Pair.of(pair.get(1), pair.get(2)));
        return pairs;
    }

    public List<Crew> getPair() {
        return pair;
    }
}
