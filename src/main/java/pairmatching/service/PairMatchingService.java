package pairmatching.service;

import java.util.ArrayList;
import java.util.List;
import pairmatching.constant.Course;
import pairmatching.domain.Content;
import pairmatching.domain.Crews;
import pairmatching.domain.MatchingResult;
import pairmatching.domain.Pair;

public class PairMatchingService {

    private Crews crews;
    private MatchingResult matchingResult;

    public void registerFileInfo(Course course, List<String> names) {
        crews = Crews.getInstance();

        for (String name : names) {
            crews.addCrew(course, name);
        }
    }

    public void init() {
        matchingResult = MatchingResult.newInstance();
        matchingResult.init();
    }

    public List<Pair> generateMatchingWithRetry(Content content, int tryCount) {
        for (int i = 0; i < tryCount; i++) {
            List<Pair> matching = matchingResult.generateMatching(crews, content);

            if (matchingResult.isPossible(matching, content)) {
                matchingResult.addResult(matching, content);
                return matching;
            }
        }

        return new ArrayList<>();
    }

    public List<Pair> getMatching(Content content) {
        return matchingResult.getMatching(content);
    }

    public void reset() {
        matchingResult.reset();
    }

    public boolean MatchingResultIsExist(Content content) {
        return matchingResult.isExist(content);
    }
}
