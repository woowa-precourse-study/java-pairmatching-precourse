package pairmatching.command.impl;

import java.util.List;
import pairmatching.command.Command;
import pairmatching.command.CommandResponse;
import pairmatching.constant.Course;
import pairmatching.constant.Level;
import pairmatching.constant.Mission;
import pairmatching.service.PairMatchingService;
import pairmatching.util.InputParser;
import pairmatching.util.Retry;
import pairmatching.view.InputView;
import pairmatching.view.model.FeatureAModel;

public class FeatureACommand implements Command<FeatureACommand> {

    private final PairMatchingService service;

    public FeatureACommand(PairMatchingService service) {
        this.service = service;
    }

    @Override
    public CommandResponse execute() {
        Retry.retryUntilSuccess(() -> {
            String readContents = InputView.readContents();
            List<String> contents = InputParser.parseContents(readContents);

            Course course = Course.from(contents.get(0));
            Level level = Level.from(contents.get(1));
            Mission mission = Mission.from(contents.get(2));

            if (service.MatchingResultIsExist(course, level, mission)) {
                Retry.retryUntilSuccess(() -> {

                });
            }

            service.greetTarget(contents)}
        );
        return CommandResponse.keepGoing(new FeatureAModel(string));
    }
}
