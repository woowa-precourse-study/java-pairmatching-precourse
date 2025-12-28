package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readInput(){
        System.out.println("입력받는 메서드");
        String input = Console.readLine();
        return input;
    }

    // 오류 발생 지점 부터 재 입력 받는 로직
    public String readInputWithRetry(){
        System.out.println("이 부분이 반복 됩니다.");
        try{
            String input = Console.readLine();
            return input;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}