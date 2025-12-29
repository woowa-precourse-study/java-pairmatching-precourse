package pairmatching.command.impl;

import pairmatching.command.Command;
import pairmatching.service.PairMatchingService;
import pairmatching.view.OutputView;

public class FeatureCCommand implements Command {

    private final PairMatchingService service;

    public FeatureCCommand(PairMatchingService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        service.reset();

        OutputView.printReset();
    }
}
