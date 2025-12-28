package pairmatching.domain;

import java.util.List;

public interface PairGenerator {

    List<Pair> generate(List<Crew> crews);
}
