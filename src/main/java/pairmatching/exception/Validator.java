package pairmatching.exception;

import java.util.Set;

public interface Validator {
    void validate(String input);


    static void validateNotBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 빈 값은 입력할 수 없습니다.");
        }
    }

    static void validateChoice(String input) {
        if (!Set.of("1","2","3","Q").contains(input)) {
            throw new IllegalArgumentException("[ERROR] 입력이 올바르지 않습니다.");
        }
    }

}
