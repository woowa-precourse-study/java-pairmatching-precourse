package pairmatching.factory;

import java.util.List;
import pairmatching.domain.MatchingCondition;
import pairmatching.domain.vo.Course;
import pairmatching.domain.vo.Level;
import pairmatching.domain.vo.Mission;

public class PairMatchingFactory {

    public static MatchingCondition createCondition(List<String> input) {
        String courseValue = input.get(0);
        String levelValue = input.get(1);
        String missionValue = input.get(2);

        Course course = Course.of(courseValue);
        Level level = Level.of(levelValue);
        Mission mission = Mission.of(missionValue);

        return MatchingCondition.from(course, level, mission);
    }
}
