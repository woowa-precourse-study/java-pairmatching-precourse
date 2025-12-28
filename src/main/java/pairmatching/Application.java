package pairmatching;

import pairmatching.controller.MatchingController;
import pairmatching.service.MatchingService;

import java.util.HashMap;
import java.util.Map;


public class Application {

    public static void main(String[] args) {
        MatchingService matchingService = new MatchingService();
        MatchingController matchingController = new MatchingController(matchingService);
        try {
            matchingController.run();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }



}
