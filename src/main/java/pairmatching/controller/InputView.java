package pairmatching.controller;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.exception.Validator;

import java.util.List;
import java.util.NoSuchElementException;

public class InputView {

    public String readFunction() {
        System.out.println("""
                기능을 선택하세요.
                1. 페어 매칭
                2. 페어 조회
                3. 페어 초기화
                Q. 종료
                """);
        String input = readInput(List.of(
                Validator::validateNotBlank,
                Validator::validateChoice
        ));
        return input;
    }

    public String readChoice() {
        System.out.println("""
                과정, 레벨, 미션을 선택하세요.
                ex) 백엔드, 레벨1, 자동차경주
                """);
        String input = readInput(List.of(
                Validator::validateNotBlank,
                Validator::validateChoice
        ));
        return input;
    }




    private String readInput(List<Validator> validators) {
        try{
            String input = Console.readLine().trim();
            for (Validator v : validators) {
                v.validate(input);
            }
            return input;
        } catch(NoSuchElementException e){
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }

    }
}