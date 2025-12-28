package pairmatching.view.model;

import java.util.List;
import java.util.Set;
import pairmatching.domain.Crew;

public class FeatureCModel implements ViewModel {

    @Override
    public void accept(ViewModelVisitor visitor) {
        visitor.visit(new FeatureCModel());
    }
}
