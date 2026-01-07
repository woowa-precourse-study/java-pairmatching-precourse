package pairmatching.domain;

import java.util.List;

public class Result {
    private final Choice choice;
    private final List<Pair> pairs;

    public Result(Choice choice, List<Pair> pairs) {
        this.choice = choice;
        this.pairs = pairs;
    }

    public boolean hasHistory(Choice choice){
        return (choice.equals(choice));
    }
}
