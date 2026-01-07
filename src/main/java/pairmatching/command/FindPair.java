package pairmatching.command;

import pairmatching.controller.InputView;
import pairmatching.domain.Machine;
import pairmatching.service.Service;

public class FindPair implements Command {
    private final InputView inputView;
    private final Service service;
    private final Machine machine;

    public FindPair(Service service, Machine machine) {
        this.inputView=new InputView();;
        this.service = service;
        this.machine = machine;
    }

    @Override
    public void execute() {
        findPair();
    }

    public void findPair() {

    }
}

