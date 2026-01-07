package pairmatching.command;

import pairmatching.controller.InputView;

public class FindPair implements Command {
    private final InputView inputView;

    public FindPair(InputView inputView) {
        this.inputView=inputView;
    }

    @Override
    public void execute() {
        findPair();
    }

    public void findPair() {

    }
}

