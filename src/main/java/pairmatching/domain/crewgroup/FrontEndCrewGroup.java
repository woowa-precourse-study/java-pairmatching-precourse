package pairmatching.domain.crewgroup;

import java.util.List;
import pairmatching.domain.CrewGroup;
import pairmatching.domain.PairGenerator;
import pairmatching.domain.vo.Course;
import pairmatching.domain.vo.Crew;
import pairmatching.domain.vo.Pair;

public class FrontEndCrewGroup implements CrewGroup {
    private final List<Crew> crews;

    public FrontEndCrewGroup(List<Crew> crews) {
        this.crews = crews;
    }

    @Override
    public List<Pair> generatePairs(PairGenerator generator) {
        return generator.generate(Course.FRONTEND, crews);
    }
}
