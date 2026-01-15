package pairmatching.utils;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class RandomGenerator {

    public static List<String> getRandom(List<String> crews) {
        return Randoms.shuffle(crews);
    }

}

