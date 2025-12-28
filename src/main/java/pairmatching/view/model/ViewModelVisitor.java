package pairmatching.view.model;

public interface ViewModelVisitor {
    void visit(FeatureAModel model);
    void visit(QuitModel model);
}
