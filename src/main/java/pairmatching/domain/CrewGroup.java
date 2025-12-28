package pairmatching.domain;

import java.util.List;

public interface CrewGroup {

    List<Pair> generatePairs(PairGenerator generator);
}
