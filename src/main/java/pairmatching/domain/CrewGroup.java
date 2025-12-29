package pairmatching.domain;

import java.util.List;
import pairmatching.domain.vo.Pair;

public interface CrewGroup {

    List<Pair> generatePairs(PairGenerator generator);
}
