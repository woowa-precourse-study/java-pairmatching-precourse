package pairmatching.service;

import java.util.List;
import pairmatching.constant.Course;
import pairmatching.domain.Crews;
import pairmatching.domain.MatchingHistory;
import pairmatching.domain.MatchingResult;

public class PairMatchingService {

    private Crews crews;
    private MatchingHistory matchingHistory;
    private MatchingResult matchingResult;

    public void registerFileInfo(Course course, List<String> names) {
        crews = Crews.newInstance();

        for (String name : names) {
            crews.addCrew(course, name);
        }

        System.out.println(crews.getCrews());
    }
}
