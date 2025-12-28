package pairmatching.controller.command;

import java.util.List;
import pairmatching.controller.Command;
import pairmatching.domain.MatchingCondition;
import pairmatching.domain.Pair;
import pairmatching.domain.PairMatchingProgram;
import pairmatching.factory.PairMatchingFactory;
import pairmatching.util.InputParser;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;
import pairmatching.view.PrintMessage;

public class PairMatchingCommand implements Command {
    private static final String DELIMITER = ",";

    @Override
    public void execute(PairMatchingProgram program) {

        MatchingCondition condition = retryOnError(() -> {
            OutputView.printPrompt(PrintMessage.PRINT_COURSE_MISSION);
            String courseAndMission = InputView.readCourseAndMission();

            List<String> courseMission = InputParser.parse(courseAndMission);

            return PairMatchingFactory.createCondition(courseMission);
        });

        if (program.exists(condition)) {
            String overWrite = readOverWrite();
            if (overWrite.equals("아니요")) {
                return;
            }
        }

        List<Pair> pairs = program.createPairs(condition);

        OutputView.printPairs(pairs);
    }

    private String readOverWrite() {
        return retryOnError(() -> {
            OutputView.printPrompt(PrintMessage.PRINT_MATCHING_AGAIN);
            return InputView.readOverWrite();
        });
    }
}
