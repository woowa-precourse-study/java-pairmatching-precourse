package pairmatching.view;

import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.vo.Crew;
import pairmatching.domain.vo.Pair;

public class OutputView {

    public static void printPrompt(String message) {
        System.out.println(message);
    }

    public static void printError(String message) {
        System.out.println(message);
    }

    public static void printPairs(List<Pair> pairs) {
        System.out.println("페어 매칭 결과입니다.\n");
        for (Pair pair : pairs) {
            List<Crew> crews = pair.getCrews();

            String printPair = crews.stream()
                    .map(Crew::getName)
                    .collect(Collectors.joining(" : "));

            System.out.println(printPair);
        }

        System.out.println();
    }
}
