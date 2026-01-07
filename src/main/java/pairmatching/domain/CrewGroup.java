package pairmatching.domain;

import pairmatching.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class CrewGroup {
    private final List<Crew> crews=new ArrayList<>();
    private final EnumMap<Level,PairGroup> pairs=new EnumMap<>(Level.class);

    public CrewGroup() {
        for (Level level:Level.values()){
            pairs.put(level,new PairGroup());
        }
    }

    public void add(Crew crew){
        crews.add(crew);
    }

    public void match(Level level){
        List<Crew> randomCrew=RandomGenerator.getRandomNumber(crews);
        // TODO: 2명씩 매칭 + pairs에게 겹치는 페이 있는지 묻기
    }
}
