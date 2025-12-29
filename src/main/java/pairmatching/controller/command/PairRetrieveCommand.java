package pairmatching.controller.command;

import java.util.List;
import pairmatching.controller.Command;
import pairmatching.domain.MatchingCondition;
import pairmatching.domain.MatchingRecords;
import pairmatching.domain.PairMatchingProgram;
import pairmatching.domain.vo.Pair;
import pairmatching.exception.ErrorCode;
import pairmatching.factory.PairMatchingFactory;
import pairmatching.util.InputParser;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;
import pairmatching.view.PrintMessage;

public class PairRetrieveCommand implements Command {

    @Override
    public void execute(PairMatchingProgram program) {
        try {
            MatchingCondition condition = getCondition();

            if (!program.exists(condition)) {
                throw new IllegalArgumentException(ErrorCode.NOT_EXIST_MATCHING.getMessage());
            }

            MatchingRecords records = program.getRecords();
            List<Pair> pairs = records.getPairsWith(condition);

            OutputView.printPairs(pairs);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
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
}
