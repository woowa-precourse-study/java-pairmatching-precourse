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
        Content content;

        while (true) {
             content = getContent();

            if (service.MatchingResultIsExist(content)) {
                break;
            }

            OutputView.printErrorMessage(new IllegalArgumentException(ErrorMessage.NO_EXIST_MATCHING_RESULT.getErrorMessage()));
        }

        List<Pair> matching =  service.getMatching(content);

        OutputView.printMatching(matching);
    }

    private static Content getContent() {
        return Retry.retryUntilSuccess(() -> {
            String readContents = InputView.readContents();
            List<String> contents = InputParser.parseContents(readContents);

            Course course = Course.from(contents.get(0).trim());
            Level level = Level.from(contents.get(1).trim());
            Mission mission = level.getMission(contents.get(2).trim());

            return Content.of(course, level, mission);
        });
    }
}
