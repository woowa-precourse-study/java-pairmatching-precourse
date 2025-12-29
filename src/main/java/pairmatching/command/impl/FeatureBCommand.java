package pairmatching.command.impl;

import java.util.List;
import pairmatching.command.Command;
import pairmatching.constant.Course;
import pairmatching.constant.ErrorMessage;
import pairmatching.constant.Level;
import pairmatching.constant.Mission;
import pairmatching.domain.Content;
import pairmatching.domain.Pair;
import pairmatching.service.PairMatchingService;
import pairmatching.util.InputParser;
import pairmatching.util.Retry;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class FeatureBCommand implements Command {

    private final PairMatchingService service;

    public FeatureBCommand(PairMatchingService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        List<Pair> matching = Retry.retryUntilSuccess(() -> {
            String readContents = InputView.readContents();
            List<String> rawContents = InputParser.parseContents(readContents);

            Course course = Course.from(rawContents.get(0).trim());
            Level level = Level.from(rawContents.get(1).trim());
            Mission mission = Mission.from(rawContents.get(2).trim());

            Content content = Content.of(course, level, mission);

            if (!service.MatchingResultIsExist(content)) {
                throw new IllegalArgumentException(ErrorMessage.NO_EXIST_MATCHING_RESULT.getErrorMessage());
            }

            return service.getMatching(content);
        });

        OutputView.printMatching(matching);
    }
}
