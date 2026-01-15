package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pair {
    private final List<String> crews=new ArrayList<>();

    public void add(String crew){
        crews.add(crew);
    }

    public List<String> getCrews(){
        return crews;
    }

    public void validateDifferent(Pair pair){
        Set<String> newCrew = new HashSet<>(pair.getCrews());
        if (newCrew.containsAll(crews) || crews.containsAll(newCrew)){
            throw new IllegalArgumentException("[ERROR] 이미 매칭된적 있는 페어입니다.");
        }

    }


}
