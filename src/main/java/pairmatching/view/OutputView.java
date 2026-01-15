package pairmatching.view;

import java.util.List;

public class OutputView{
    public void printResult(){
        System.out.println("test");
    }

    public static void printPairList(List<List<String>> pairList){
        System.out.println("페어 매칭 결과입니다.");
        for(List<String> pair : pairList){
            if(pair.size() == 3){
                System.out.println(pair.get(0) + " : " + pair.get(1) + " : " + pair.get(2));
            }else{
                System.out.println(pair.get(0) + " : " + pair.get(1));
            }

        }
    }
}