package pairmatching.command;

import pairmatching.controller.InputView;

public class PairReset implements Command {
    private final InputView inputView;

    public PairReset(InputView inputView) {
        this.inputView=inputView;
    }

    @Override
    public void execute() {
        pairReset();
    }

    public void pairReset() {

    }
}

