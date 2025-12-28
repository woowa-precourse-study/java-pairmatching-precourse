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

    public static void printMenu(){
        System.out.println("#############################################\n"
                + "과정: 백엔드 | 프론트엔드\n"
                + "미션:\n"
                + "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n"
                + "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n"
                + "  - 레벨3: \n"
                + "  - 레벨4: 성능개선 | 배포\n"
                + "  - 레벨5: \n"
                + "############################################");
    }
}