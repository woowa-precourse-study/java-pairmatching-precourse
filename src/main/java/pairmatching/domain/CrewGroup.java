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

        Deque<String> deque = new LinkedList<>(randomCrew);
        PairGroup pairGroup = pairs.getOrDefault(level,new PairGroup());


        List<Pair> newPairs = new ArrayList<>();
        while(!deque.isEmpty()) {
            Pair pair = new Pair();
            pair.add(deque.pollFirst());
            pair.add(deque.pollFirst());

            if (deque.size()==1){
                pair.add(deque.pollFirst());
            }
            pairGroup.validateNewMatched(pair);
            newPairs.add(pair);
        }
        for (Pair pair:newPairs){
            pairGroup.add(pair);
        }
        pairs.put(level,pairGroup);
    }

    public List<Pair> getPairs(Level level){
        return pairs.get(level).getPairs();
    }
}
