package pairmatching.command;

import pairmatching.controller.MatchingController;

public class PairReset implements Command{
    private final MatchingController matchingController;

    public PairReset(MatchingController matchingController){
        this.matchingController=matchingController;
    }

    @Override
    public void execute() {
        matchingController.pairReset();
    }

}
