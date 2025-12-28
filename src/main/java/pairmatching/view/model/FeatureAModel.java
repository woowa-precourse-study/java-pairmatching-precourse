package pairmatching.view.model;

import java.util.List;
import java.util.Set;
import pairmatching.domain.Crew;

public class FeatureAModel implements ViewModel {

    private final List<Set<Crew>> matching;

    public FeatureAModel(List<Set<Crew>> matching) {
        this.matching = matching;
    }

    @Override
    public void accept(ViewModelVisitor visitor) {
        visitor.visit(this);
    }

    public List<Set<Crew>> getMatching() {
        return matching;
    }
}
