package pairmatching.command;

import pairmatching.controller.MatchingController;

public class PairMatching implements Command{
    private final MatchingController matchingController;

    public PairMatching(MatchingController matchingController){
        this.matchingController=matchingController;
    }

    @Override
    public void execute() {
        matchingController.pairMatching();
    }

}
