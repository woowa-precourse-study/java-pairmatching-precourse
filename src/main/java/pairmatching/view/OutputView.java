package pairmatching.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.mockito.internal.util.StringUtil;
import pairmatching.domain.Crew;
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
            List<Set<Crew>> matching = model.getMatching();
            System.out.println("\n페어 매칭 결과입니다.");
            for (Set<Crew> crews : matching) {
                ArrayList<Crew> pair = new ArrayList<>(crews);
                List<String> pairList = pair.stream()
                        .map(Crew::toString)
                        .collect(Collectors.toList());
                System.out.println(String.join(" : ", new ArrayList<>(pairList)));
            }
            System.out.println();
        }

        @Override
        public void visit(QuitModel model) {
            // 출력 없음
        }
    }
}
