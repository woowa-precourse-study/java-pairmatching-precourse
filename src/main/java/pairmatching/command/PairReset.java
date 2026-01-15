package pairmatching.command;

import pairmatching.controller.InputView;
import pairmatching.controller.OutputView;
import pairmatching.domain.Machine;
import pairmatching.service.Service;

public class PairReset implements Command {
    private final InputView inputView;
    private final Service service;
    private final Machine machine;

    public PairReset(Service service,Machine machine) {
        this.inputView=new InputView();
        this.service=service;
        this.machine=machine;
    }

    @Override
    public void execute() {
        pairReset();
    }

    public void pairReset() {
        machine.reset();
        OutputView.printReset();
    }
}

