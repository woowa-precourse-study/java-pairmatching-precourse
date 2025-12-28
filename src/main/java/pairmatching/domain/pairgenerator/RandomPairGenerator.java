package pairmatching.domain.pairgenerator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Pair;
import pairmatching.domain.PairGenerator;

public class RandomPairGenerator implements PairGenerator {
    @Override
    public List<Pair> generate(Course course, List<Crew> crews) {
        List<String> crewNames = crews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());

        List<String> shuffleNames = Randoms.shuffle(crewNames);

        Deque<Crew> queue = shuffleNames.stream()
                .map(name -> new Crew(course, name))
                .collect(Collectors.toCollection(ArrayDeque::new));

        List<Pair> pairs = new ArrayList<>();

        while (queue.size() >= 2) {
            Crew first = queue.poll();
            Crew second = queue.poll();

            if (queue.size() == 1) {
                Crew third = queue.poll();
                pairs.add(Pair.from(first, second, third));
                break;
            }

            pairs.add(Pair.from(first, second));
        }

        return pairs;
    }

}
