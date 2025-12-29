package pairmatching;

import pairmatching.controller.PairMatchingController;
import pairmatching.factory.ApplicationFactory;

public class Application {
    public static void main(String[] args) {
        ApplicationFactory factory = new ApplicationFactory();
        PairMatchingController controller = factory.controller();

        controller.run();
    }
}
