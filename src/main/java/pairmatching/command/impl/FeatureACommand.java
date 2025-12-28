package pairmatching.command.impl;

import pairmatching.command.Command;
import pairmatching.command.CommandResponse;
import pairmatching.service.PairMatchingService;
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
        String string = InputView.read();
//        String name = Retry.retryUntilSuccess(() -> service.greetTarget(string));
        return CommandResponse.keepGoing(new FeatureAModel(string));
    }
}
