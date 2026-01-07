package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Pair {
    private final List<String> crews=new ArrayList<>();

    public void add(String crew){
        crews.add(crew);
    }

    public List<String> getCrews(){
        return crews;
    }


}
