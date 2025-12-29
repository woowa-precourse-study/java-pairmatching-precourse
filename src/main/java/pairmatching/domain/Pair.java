package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Pair {

    public final List<Crew> pair;

    private Pair(List<Crew> pair) {
        this.pair = pair;
    }

    public static Pair of(Crew... crews) {
        return new Pair(new ArrayList<>(Arrays.asList(crews)));
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

    public void addCrew(Crew crew) {
        pair.add(crew);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Pair pair1 = (Pair) object;
        return new HashSet<>(pair).equals(new HashSet<>(pair1.pair));
    }

    public boolean contains(List<Pair> pairs) {
        for (Pair pair : pairs) {
            return this.equals(pair);
        }
        return false;
    }
}
