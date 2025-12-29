package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import pairmatching.constant.Mission;

public class MatchingResult {

    private Map<Content, List<Pair>> matchingResults;

    public static MatchingResult newInstance() {
        return new MatchingResult();
    }

    public void init() {
        matchingResults = new HashMap<>();
    }

    public boolean isExist(Content content) {
        return matchingResults.containsKey(content);
    }

    public List<Pair> generateMatching(Crews crews, Content content) {
        List<String> preCrews = crews.getCrews().get(content.getCourse()).stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
        List<String> shuffleCrews = new ArrayList<>(Randoms.shuffle(preCrews));

        return getPairs(crews, shuffleCrews);
    }

    private List<Pair> getPairs(Crews crews, List<String> shuffleCrews) {
        List<Pair> matching = new ArrayList<>();

        while (shuffleCrews.size() > 1) {
            Crew firstCrew = crews.getCrew(shuffleCrews.remove(0));
            Crew secondCrew = crews.getCrew(shuffleCrews.remove(0));
            Pair pair = Pair.of(firstCrew, secondCrew);

            if (shuffleCrews.size() == 1) {
                pair.addCrew(crews.getCrew(shuffleCrews.remove(0)));
            }

            matching.add(pair);
        }

        return matching;
    }

    public boolean isPossible(List<Pair> matching, Content content) {
        for (Mission mission : content.getLevel().getMissions()) {
            if (mission.equals(content.getMission())) {
                continue;
            }

            Content key = Content.of(content.getCourse(), content.getLevel(), mission);
            List<Pair> matchingHistory = matchingResults.get(key);

            if (matchingHistory == null) {
                continue;
            }

            for (Pair pair : matching) {
                List<Pair> pairs = pair.getPairs();
                return pairs.stream()
                        .anyMatch(matchingHistory::contains);
            }
        }
        return true;
    }

    public void addResult(List<Pair> matching, Content content) {
        matchingResults.put(content, matching);
    }

    public List<Pair> getMatching(Content content) {
        return matchingResults.get(content);
    }

    public void reset() {
        matchingResults = new HashMap<>();
    }
}
