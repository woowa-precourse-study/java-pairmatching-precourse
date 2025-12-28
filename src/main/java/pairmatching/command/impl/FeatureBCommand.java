package pairmatching.command.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import pairmatching.command.Command;
import pairmatching.command.CommandResponse;
import pairmatching.command.RematchOption;
import pairmatching.constant.Course;
import pairmatching.constant.ErrorMessage;
import pairmatching.constant.Level;
import pairmatching.constant.Mission;
import pairmatching.domain.Crew;
import pairmatching.service.PairMatchingService;
import pairmatching.util.InputParser;
import pairmatching.util.Retry;
import pairmatching.view.InputView;
import pairmatching.view.model.FeatureAModel;
import pairmatching.view.model.FeatureBModel;

public class FeatureBCommand implements Command<FeatureBCommand> {

    private final PairMatchingService service;

    public FeatureBCommand(PairMatchingService service) {
        this.service = service;
    }

    @Override
    public CommandResponse execute() {
        return Retry.retryUntilSuccess(() -> {
            String readContents = InputView.readContents();
            List<String> contents = InputParser.parseContents(readContents);

            Course course = Course.from(contents.get(0).trim());
            Level level = Level.from(contents.get(1).trim());
            Mission mission = Mission.from(contents.get(2).trim());

            if (!service.MatchingResultIsExist(course, level, mission)) {
                throw new IllegalArgumentException(ErrorMessage.NO_EXIST_MATCHING_RESULT.getErrorMessage());
            }

            List<Set<Crew>> matching = service.getMatching(course, level, mission);

            return CommandResponse.keepGoing(new FeatureBModel(matching));
        });
    }
}
