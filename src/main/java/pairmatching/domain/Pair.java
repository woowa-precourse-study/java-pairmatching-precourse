package pairmatching.domain;

import java.util.List;

public class Pair {
    private final List<Crew> crews;

    public Pair(List<Crew> crews) {
        this.crews = crews;
    }

    public static Pair from(Crew... crews) {
        return new Pair(List.of(crews));
    }

    public List<Crew> getCrews() {
        return List.copyOf(crews);
    }
}
