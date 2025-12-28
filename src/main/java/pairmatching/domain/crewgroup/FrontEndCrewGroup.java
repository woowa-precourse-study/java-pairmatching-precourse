package pairmatching.domain.crewgroup;

import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.CrewGroup;
import pairmatching.domain.Pair;
import pairmatching.domain.PairGenerator;

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
