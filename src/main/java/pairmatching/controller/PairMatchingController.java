package pairmatching.controller;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import pairmatching.domain.PairMatchingProgram;
import pairmatching.exception.ErrorCode;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;
import pairmatching.view.PrintMessage;

public class PairMatchingController {
    private final Map<String, Command> commands;
    private PairMatchingProgram program;

    public PairMatchingController(Map<String, Command> commands, PairMatchingProgram program) {
        this.commands = commands;
        this.program = program;
    }

    public void run() {
        while (true) {
            String inputCommand = readCommand();

            if (inputCommand.equals("Q")) {
                break;
            }
            Command command = commands.get(inputCommand);
            command.execute(program);
        }
    }

    private String readCommand() {
        return retryOnError(() -> {
            OutputView.printPrompt(PrintMessage.CHOOSE_COMMAND);
            String inputCommand = InputView.readCommand();

            validateCommand(inputCommand);

            return inputCommand;
        });
    }

    private <T> T retryOnError(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private void validateCommand(String inputCommand) {
        Set<String> commandKeys = commands.keySet();

        if (!commandKeys.contains(inputCommand)) {
            throw new IllegalArgumentException(ErrorCode.NOT_VALID_COMMAND.getMessage());
        }
    }
}
