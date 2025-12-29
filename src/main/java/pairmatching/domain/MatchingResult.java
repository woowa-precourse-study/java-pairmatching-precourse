package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import pairmatching.constant.Course;
import pairmatching.constant.Level;
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
        List<String> shuffleCrews = Randoms.shuffle(preCrews);

        return getPairs(crews, shuffleCrews);
    }

    private List<Pair> getPairs(Crews crews, List<String> shuffleCrews) {
        List<Pair> matching = new ArrayList<>();
        int index = 0;
        while (index < shuffleCrews.size()) {
            Crew firstCrew = crews.getCrew(shuffleCrews.get(index++));
            Crew secondCrew = crews.getCrew(shuffleCrews.get(index++));
            Crew thirdCrew;

            if (index == shuffleCrews.size() - 1) {
                thirdCrew = crews.getCrew(shuffleCrews.get(index++));
                matching.add(Pair.of(firstCrew, secondCrew, thirdCrew));
                continue;
            }

            matching.add(Pair.of(firstCrew, secondCrew));
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
