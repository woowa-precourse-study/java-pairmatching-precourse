package pairmatching.controller;

import pairmatching.domain.Pairs;

import java.util.List;

public class OutputView {

    public static void printCourseInfo() {
        System.out.println(Message.PRINT_COURSE_AND_MISSION);
    }

    public static void printMatchingResult(List<Pairs> pairs) {
        System.out.println("페어 매칭 결과입니다.");
        for (Pairs pair: pairs){
            System.out.println(String.join(" : ",pair.getPairs()));
        }
    }

}

