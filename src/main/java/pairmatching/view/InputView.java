package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String readCommand(){
        System.out.println("기능을 선택하세요.");
        System.out.println("1. 페어 매칭\n"
                + "2. 페어 조회\n"
                + "3. 페어 초기화\n"
                + "Q. 종료");
        try{
            return Console.readLine();
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }





    public static String readInput(){
        System.out.println("입력받는 메서드");
        String input = Console.readLine();
        return input;
    }



    // 오류 발생 지점 부터 재 입력 받는 로직
    public static String readInputWithRetry(){
        System.out.println("이 부분이 반복 됩니다.");
        try{
            String input = Console.readLine();
            return input;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}