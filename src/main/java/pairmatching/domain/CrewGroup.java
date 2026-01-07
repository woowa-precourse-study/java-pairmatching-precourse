package pairmatching.domain;

import pairmatching.utils.RandomGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class CrewGroup {
    private final List<Crew> crews = new ArrayList<>();
    private final EnumMap<Level, PairGroup> pairs = new EnumMap<>(Level.class);

    public CrewGroup() {
        for (Level level : Level.values()) {
            pairs.put(level, new PairGroup());
        }
    }

    public void add(Crew crew) {
        crews.add(crew);
    }

    public void match(Level level) {
        List<String> crewNames = crews.stream()
                .map(Crew::getName).collect(Collectors.toList());
        List<String> randomCrew = RandomGenerator.getRandom(crewNames);
        PairGroup pairGroup = new PairGroup();
        for (int i = 0; i < (randomCrew.size()/2)*2; i += 2) {
            Pair pair = new Pair();
            pair.add(randomCrew.get(i));
            pair.add(randomCrew.get(i+1));
            pairGroup.add(pair);
            // TODO: 홀수 일떄 마지막 추가
        }
        pairs.put(level,pairGroup);
        // TODO: 2명씩 매칭 + pairs에게 겹치는 페이 있는지 묻기
    }

    public List<Pair> getPairs(Level level){
        return pairs.get(level).getPairs();
    }
}
