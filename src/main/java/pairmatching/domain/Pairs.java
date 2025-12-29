package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Pairs {
    private final List<String> pairs;

    public Pairs(List<String> crews){
        this.pairs=new ArrayList<>(crews);
    }

    public List<String> getPairs(){
        return List.copyOf(pairs);
    }
}
