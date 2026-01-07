package pairmatching.controller;

import pairmatching.command.*;
import pairmatching.domain.Machine;
import pairmatching.service.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Controller {
    private Map<String, Command> commands = new HashMap<>();
    private final Machine machine;
    private final InputView inputView;
    private final Service service;
    public static final int MAX_RETRY = 10;

    public Controller(Service service) {
        this.inputView = new InputView();
        this.service = service;
        this.machine = new Machine();
    }

    public void run() {
        initCommands();
        while(true){
            String function = doRetry(inputView::readFunction);

            if (function.equals("Q")){
                return;
            }

            Command command = commands.get(function);
            command.execute();
        }

    }

    private void initCommands() {
        commands.put("1", new PairMatching(service,machine));
        commands.put("2", new FindPair(inputView));
        commands.put("3", new PairReset(inputView));
        commands.put("Q", new Quit());
    }

    private <T> T doRetry(Supplier<T> action) {
        int retry = 0;
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                retry++;
                System.out.println(e.getMessage());

                if (retry >= MAX_RETRY) {
                    throw new IllegalStateException("입력 횟수를 초과했습니다.");
                }
            }
        }
    }


}

