package pairmatching.command.impl;

import pairmatching.command.Command;
import pairmatching.command.CommandResponse;

public class QuitCommand implements Command<QuitCommand> {

    @Override
    public CommandResponse execute() {
        return CommandResponse.exit();
    }
}
