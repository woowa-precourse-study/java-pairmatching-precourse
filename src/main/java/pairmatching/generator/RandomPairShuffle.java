package pairmatching.generator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomPairShuffle implements RandomGenerator{

    @Override
    public List<String> getShuffledList(List<String> memberList){
        List<String> list = Randoms.shuffle(memberList);
        return list;
    }
}
