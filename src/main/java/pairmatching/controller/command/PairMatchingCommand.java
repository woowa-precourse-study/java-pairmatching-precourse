package pairmatching.controller.command;

import java.util.List;
import pairmatching.controller.Command;
import pairmatching.domain.MatchingCondition;
import pairmatching.domain.PairMatchingProgram;
import pairmatching.domain.vo.Pair;
import pairmatching.factory.PairMatchingFactory;
import pairmatching.util.InputParser;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;
import pairmatching.view.PrintMessage;

public class PairMatchingCommand implements Command {

    @Override
    public void execute(PairMatchingProgram program) {
        List<Pair> pairs = getMatchingCondition(program);

        OutputView.printPairs(pairs);
    }

    private List<Pair> getMatchingCondition(PairMatchingProgram program) {
        while (true) {
            try {
                MatchingCondition condition = getCondition();

                if (program.exists(condition)) {
                    String overWrite = readOverWrite();
                    if (overWrite.equals("아니요")) {
                        continue;
                    }
                }

                return program.createPairs(condition);
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private MatchingCondition getCondition() {
        return retryOnError(() -> {
            OutputView.printPrompt(PrintMessage.PRINT_COURSE_MISSION);
            String courseAndMission = InputView.readCourseAndMission();

            List<String> courseMission = InputParser.parse(courseAndMission);

            return PairMatchingFactory.createCondition(courseMission);
        });
    }

    private String readOverWrite() {
        return retryOnError(() -> {
            OutputView.printPrompt(PrintMessage.PRINT_MATCHING_AGAIN);
            return InputView.readOverWrite();
        });
    }
}
