package pairmatching.domain.vo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public boolean equals(Object obj) {
        Pair target = (Pair) obj;

        Set<Crew> c1 = new HashSet<>(crews);
        Set<Crew> c2 = new HashSet<>(target.crews);

        for (Crew crew : c1) {
            if (!c2.contains(crew)) {
                return false;
            }
        }

        return true;
    }
}
