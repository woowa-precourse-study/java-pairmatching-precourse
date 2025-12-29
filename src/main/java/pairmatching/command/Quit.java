package pairmatching.command;

import pairmatching.controller.MatchingController;

public class Quit implements Command{
    private final MatchingController matchingController;

    public Quit (MatchingController matchingController){
        this.matchingController=matchingController;
    }

    @Override
    public void execute() {
        matchingController.quit();
    }

}