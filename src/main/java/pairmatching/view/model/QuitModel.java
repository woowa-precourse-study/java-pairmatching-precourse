package pairmatching.view.model;

import pairmatching.view.model.ViewModel;
import pairmatching.view.model.ViewModelVisitor;

public class QuitModel implements ViewModel {

    @Override
    public void accept(ViewModelVisitor visitor) {
        visitor.visit(this);
    }
}
