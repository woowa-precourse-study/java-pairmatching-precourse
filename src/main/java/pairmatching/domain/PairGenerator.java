package pairmatching.domain;

import java.util.List;
import pairmatching.domain.vo.Course;
import pairmatching.domain.vo.Crew;
import pairmatching.domain.vo.Pair;

public interface PairGenerator {

    List<Pair> generate(Course course, List<Crew> crews);
}
