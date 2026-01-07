package pairmatching.service;

import pairmatching.domain.Choice;
import pairmatching.domain.Course;
import pairmatching.domain.Level;

import java.util.List;

public class Service {
    public Choice getChoice(List<String> inputs){
        Course course=Course.of(inputs.get(0));
        Level level=Level.of(inputs.get(1));
        String mission=inputs.get(2);
        level.validateExistMission(mission);
        return new Choice(course,level,mission);
    }
}
