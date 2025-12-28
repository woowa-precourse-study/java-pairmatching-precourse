package pairmatching.command;

import pairmatching.view.model.QuitModel;
import pairmatching.view.model.ViewModel;

public class CommandResponse {

    private final Flow flow;
    private final ViewModel model;

    public CommandResponse(Flow flow, ViewModel model) {
        this.flow = flow;
        this.model = model;
    }

    public static CommandResponse keepGoing(ViewModel model) {
        return new CommandResponse(Flow.CONTINUE, model);
    }

    public static CommandResponse exit() {
        return new CommandResponse(Flow.EXIT, new QuitModel());
    }

    public Flow getFlow() {
        return flow;
    }

    public ViewModel getModel() {
        return model;
    }
}
