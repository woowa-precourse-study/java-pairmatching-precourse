package pairmatching.view;

import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Crew;
import pairmatching.domain.Pair;

public class OutputView {

    private OutputView() {
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public static void printMatching(List<Pair> matching) {
        System.out.println("\n페어 매칭 결과입니다.");
        for (Pair pair : matching) {
            List<String> crews = pair.getPair().stream()
                    .map(Crew::getName)
                    .collect(Collectors.toList());
            System.out.println(String.join(" : ", crews));
        }
        System.out.println();
    }

    public static void printReset() {
        System.out.println("\n초기화 되었습니다.");
    }
}
