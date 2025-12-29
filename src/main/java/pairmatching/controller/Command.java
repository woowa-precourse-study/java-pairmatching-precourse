package pairmatching.controller;

import java.util.function.Supplier;
import pairmatching.domain.PairMatchingProgram;
import pairmatching.view.OutputView;

public interface Command {
    void execute(PairMatchingProgram program);

    default <T> T retryOnError(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
