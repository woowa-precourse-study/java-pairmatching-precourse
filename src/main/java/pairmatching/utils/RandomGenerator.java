package pairmatching.utils;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class RandomGenerator {

    public static List<String> getRandomNames(List<String> names) {
        return Randoms.shuffle(names);
    }

}
