package pairmatching.controller.command;

import pairmatching.controller.Command;
import pairmatching.domain.PairMatchingProgram;

public class PairResetCommand implements Command {
    @Override
    public void execute(PairMatchingProgram program) {
        program.reset();
    }
}
