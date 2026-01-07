package pairmatching.command;

import pairmatching.controller.MatchingController;

public class PairCheck implements Command {
    private final MatchingController matchingController;

    public PairCheck(MatchingController matchingController) {
        this.matchingController = matchingController;
    }

    @Override
    public void execute() {
        matchingController.pairCheck();
    }

}
