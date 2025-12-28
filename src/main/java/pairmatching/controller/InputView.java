package pairmatching.controller;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.exception.Validator;

import java.util.List;
import java.util.NoSuchElementException;

public class InputView {
    private static String PREFIX_ERROR = "[ERROR] ";
    private static final int MAX_RETRY = 10;

    public String readChoice(){
        System.out.println("기능을 선택하세요.\n 1. 페어 매칭\n 2. 페어 조회\n 3. 페어 초기화\n Q. 종료");
        String choice = readInputWithRetry(List.of(
                Validator::validateNotBlank,
                Validator::validateInputFormat
        ));
        return choice;
    }

    public String readCourseAndMission() {
        System.out.println(Message.REQUEST_OPTIONS);
        String input = readInputWithRetry(List.of(
                Validator::validateNotBlank
        ));
        return input;
    }

    public String readRetry(){
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        String response = readInputWithRetry(List.of(
                Validator::validateNotBlank,
                Validator::validateFormat
        ));
        return response;
    }



    private String readInput(List<Validator> validators) {
        String input = Console.readLine();
        for (Validator v : validators) {
            v.validate(input);
        }
        return input;
    }


    private String readInputWithRetry(List<Validator> validators) {
        int retry = 0;
        while (true) {
            try {
                return readInput(validators);
            } catch (IllegalArgumentException | NoSuchElementException e) {
                retry++;
                System.out.println(PREFIX_ERROR + e.getMessage());

                if (retry >= MAX_RETRY) {
                    throw new IllegalStateException("입력 횟수를 초과했습니다.");
                }
            }
        }
    }
}

