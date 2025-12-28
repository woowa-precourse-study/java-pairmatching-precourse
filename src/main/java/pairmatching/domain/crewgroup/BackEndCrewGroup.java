package pairmatching.domain.crewgroup;

import java.util.List;
import pairmatching.domain.Crew;
import pairmatching.domain.CrewGroup;
import pairmatching.domain.Pair;
import pairmatching.domain.PairGenerator;

public class BackEndCrewGroup implements CrewGroup {
    private final List<Crew> crews;

    public BackEndCrewGroup(List<Crew> crews) {
        this.crews = crews;
    }

    @Override
    public List<Pair> generatePairs(PairGenerator generator) {
        return generator.generate(crews);
    }
}
