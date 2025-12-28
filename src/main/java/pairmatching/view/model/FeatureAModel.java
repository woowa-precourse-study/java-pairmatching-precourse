package pairmatching.view.model;

import pairmatching.view.model.ViewModel;
import pairmatching.view.model.ViewModelVisitor;

public class FeatureAModel implements ViewModel {

    private final String name;

    public FeatureAModel(String name) {
        this.name = name;
    }

    @Override
    public void accept(ViewModelVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }
}
