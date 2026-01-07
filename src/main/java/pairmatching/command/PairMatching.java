package pairmatching.command;

import pairmatching.controller.InputView;

public class PairMatching implements Command {
    private final InputView inputView;

    public PairMatching(InputView inputView) {
        this.inputView=inputView;
    }

    @Override
    public void execute() {
        pairMatching();
    }

    public void pairMatching() {

    }
}


