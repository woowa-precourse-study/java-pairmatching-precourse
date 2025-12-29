package pairmatching.command.impl;

import java.util.List;
import pairmatching.command.Command;
import pairmatching.command.RematchOption;
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

public class MatchingCommand implements Command {

    private final PairMatchingService service;

    public MatchingCommand(PairMatchingService service) {
        this.service = service;
    }

    @Override
    public void execute() {

        while (true) {
            Content content = getContent();

            if (isAlreadyMatched(content)) {
                continue;
            }

            // 매칭 실행
            List<Pair> matching = service.generateMatchingWithRetry(content, 3);

            if (matching.isEmpty()) {
                OutputView.printErrorMessage(new IllegalArgumentException(ErrorMessage.MATCHING_FAIL.getErrorMessage()));
                continue;
            }

            OutputView.printMatching(matching);
            break;
        }

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

    private boolean isAlreadyMatched(Content content) {
        if (service.MatchingResultIsExist(content)) {
            RematchOption rematchOption = Retry.retryUntilSuccess(() -> {
                String readRematching = InputView.readRematching();
                return RematchOption.from(readRematching);
            });

            return rematchOption.equals(RematchOption.NO);
        }
        return false;
    }
}
