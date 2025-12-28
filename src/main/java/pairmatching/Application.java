package pairmatching;

import java.util.List;
import pairmatching.view.InputView;
import pairmatching.view.MdFileReader;

public class Application {
    public static void main(String[] args) {
        // TODO 구현 진행
        MdFileReader mdFileReader = new MdFileReader();

//        1. 파일 입력 및 사전 작업
//            1. 백엔드 읽어오기
        String beFileName = "src/main/resources/backend-crew.md";
        List<String> beMember = mdFileReader.readMdFile(beFileName);

//            2. 프론트 읽어오기
        String feFileName = "src/main/resources/backend-crew.md";
        List<String> feMember = mdFileReader.readMdFile(feFileName);

//        2. 기능 입력 받기
        String command = InputView.readCommand();
//        3. 페어 매칭 기능
//            1. 과정, 레벨, 미션 입력 받기
//            2. 멤버 목록에서 페어 만들기
//                1. 새롭게 만드는 경우/같은과정,다른레벨,다른 미션,등등
//                    1. 그냥 만들 면됨
//                2. 같은 과정, 같은 레벨, 다른 미션에서 만드는 경우(if)
//                    1. 멤버끼리 비교해서 페어가 되었던 적이 있으면 다시 시도(같은레벨 다른 미션)
//            3. 해당 미션에 대한 페어 정보 저장해두기
//        4. 페어 조회 기능
//            1. 과정, 레벨, 미션 입력 받기
//            2. 과정, 레벨, 미션에 대한 페어 정보 찾기
//            3. 결과 출력하기
//                1. 페어 정보 출력
//                2. 없을 경우 에러 메시지 출력
//        5. 페어 초기화 기능
//            1. 기존에 존재하던 페어 전부 초기화 하기

    }
}
