package pairmatching.command;

import pairmatching.controller.Controller;
import pairmatching.controller.InputView;
import pairmatching.controller.OutputView;
import pairmatching.domain.Choice;
import pairmatching.domain.Machine;
import pairmatching.domain.Pair;
import pairmatching.exception.Validator;
import pairmatching.service.Service;

import java.util.List;
import java.util.function.Supplier;

public class FindPair implements Command {
    private final InputView inputView;
    private final Service service;
    private final Machine machine;

    public FindPair(Service service, Machine machine) {
        this.inputView=new InputView();
        this.service = service;
        this.machine = machine;
    }

    @Override
    public void execute() {
        findPair();
    }

    public void findPair() {
        Choice choice = doRetry(() -> {
            List<String> inputs = inputView.readChoice();
            Validator.validateParsedLength(inputs.size());
            return service.getChoice(inputs);
        });
        List<Pair> pairs = machine.getPairs(choice);
        OutputView.printPairMatching(pairs);

    }

    private <T> T doRetry(Supplier<T> action) {
        int retry = 0;
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                retry++;
                System.out.println(e.getMessage());

                if (retry >= Controller.MAX_RETRY) {
                    throw new IllegalStateException("입력 횟수를 초과했습니다.");
                }
            }
        }
    }
}

