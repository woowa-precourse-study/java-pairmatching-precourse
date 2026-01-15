package pairmatching.generator;

import java.util.List;

public interface RandomGenerator {
    List<String> getShuffledList(List<String> inputList);
}
