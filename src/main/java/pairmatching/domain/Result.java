package pairmatching.domain;

import java.util.List;

public class Result {
    private final List<Pair> pairs;

    public Result(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public List<Pair> getPairs() {
        return pairs;
    }
}
