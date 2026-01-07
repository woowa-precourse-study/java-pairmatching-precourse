package pairmatching.utils;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.Crew;

import java.util.List;

public class RandomGenerator {

    public static List<Crew> getRandomNumber(List<Crew> crews) {
        return Randoms.shuffle(crews);
    }

}

