package pairmatching.domain;

import java.util.List;

public interface PairGenerator {

    List<Pair> generate(Course course, List<Crew> crews);
}
