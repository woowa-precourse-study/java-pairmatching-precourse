package pairmatching.view;

import pairmatching.view.model.FeatureAModel;
import pairmatching.view.model.QuitModel;
import pairmatching.view.model.ViewModel;
import pairmatching.view.model.ViewModelVisitor;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String _MESSAGE = "";

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    private static final OutputViewVisitor VISITOR = new OutputViewVisitor();

    private OutputView() {}

    public static void render(ViewModel model) {
        model.accept(VISITOR);
    }

    private static final class OutputViewVisitor implements ViewModelVisitor {

        @Override
        public void visit(FeatureAModel model) {

        }

        @Override
        public void visit(QuitModel model) {
            // 출력 없음
        }
    }
}
