package pairmatching.exception;

import java.util.Set;

public interface Validator {
    void validate(String input);

    static void validateNotBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("빈 값은 입력할 수 없습니다.");
        }
    }

    static void validateInputFormat(String input) {
        if (!Set.of("1","2","3","Q").contains(input)) {
            throw new IllegalArgumentException("1,2,3,Q 중에서 입력해주세요");
        }
    }

    static void validateFormat(String input) {
        if (!Set.of("네","아니오").contains(input)) {
            throw new IllegalArgumentException("네, 아니오 중에서 입력해주세요");
        }
    }




}


