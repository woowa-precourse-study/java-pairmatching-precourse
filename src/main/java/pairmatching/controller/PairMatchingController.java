package pairmatching.controller;

import java.io.IOException;
import java.util.List;
import pairmatching.command.MenuCommandRegistry;
import pairmatching.command.MenuOption;
import pairmatching.constant.Course;
import pairmatching.service.PairMatchingService;
import pairmatching.util.Retry;
import pairmatching.util.file.FileReader;
import pairmatching.view.InputView;

public class PairMatchingController {

    private final MenuCommandRegistry registry;
    private final PairMatchingService service;

    public PairMatchingController(MenuCommandRegistry registry, PairMatchingService service) {
        this.registry = registry;
        this.service = service;
    }

    public void run() throws IOException {

        registerFileInfo();
        service.init();

        while (true) {
            MenuOption option = readOption();

            if (option.equals(MenuOption.QUIT)) {
                return;
            }

            registry.execute(option);
        }
    }

    private void registerFileInfo() throws IOException {
        FileReader fileReader = new FileReader("src/main/resources/backend-crew.md");
        List<String> names = fileReader.readLines();
        service.registerFileInfo(Course.BACKEND, names);

        fileReader = new FileReader("src/main/resources/frontend-crew.md");
        names = fileReader.readLines();
        service.registerFileInfo(Course.FRONTEND, names);
    }

    private static MenuOption readOption() {
        return Retry.retryUntilSuccess(() -> {
            String selection = InputView.readMenuSelection();
            return MenuOption.from(selection);
        });
    }
}
