package pairmatching.command.impl;

import java.util.List;
import java.util.Set;
import pairmatching.command.Command;
import pairmatching.command.CommandResponse;
import pairmatching.constant.Course;
import pairmatching.constant.ErrorMessage;
import pairmatching.constant.Level;
import pairmatching.constant.Mission;
import pairmatching.domain.Crew;
import pairmatching.service.PairMatchingService;
import pairmatching.util.InputParser;
import pairmatching.util.Retry;
import pairmatching.view.InputView;
import pairmatching.view.model.FeatureBModel;
import pairmatching.view.model.FeatureCModel;

public class FeatureCCommand implements Command<FeatureCCommand> {

    private final PairMatchingService service;

    public FeatureCCommand(PairMatchingService service) {
        this.service = service;
    }

    @Override
    public CommandResponse execute() {
        return Retry.retryUntilSuccess(() -> {

            service.reset();

            return CommandResponse.keepGoing(new FeatureCModel());
        });
    }
}
