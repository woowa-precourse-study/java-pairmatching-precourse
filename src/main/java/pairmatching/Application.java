package pairmatching;

import java.io.IOException;
import pairmatching.command.MenuCommandRegistry;
import pairmatching.controller.PairMatchingController;
import pairmatching.service.PairMatchingService;

public class Application {
    public static void main(String[] args) {
        // TODO 구현 진행
        PairMatchingService service = new PairMatchingService();
        MenuCommandRegistry registry = MenuCommandRegistry.defaultRegistry(service);
        PairMatchingController controller = new PairMatchingController(registry, service);
        try {
            controller.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
