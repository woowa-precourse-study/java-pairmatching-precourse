package pairmatching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.PairInfo;
import pairmatching.exception.ErrorMessage;
import pairmatching.generator.RandomPairShuffle;
import pairmatching.service.ShuffleService;
import pairmatching.view.InputView;
import pairmatching.view.MdFileReader;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        RandomPairShuffle shuffle = new RandomPairShuffle();
        // TODO 구현 진행
        MdFileReader mdFileReader = new MdFileReader();
        Map<String, List<String>> memberListMap = new HashMap<>();
        EnumMap<Course, List<Crew>> memberCrewMap = new EnumMap<>(Course.class);
//        1. 파일 입력 및 사전 작업
//            1. 백엔드 읽어오기
        String beFileName = "src/main/resources/backend-crew.md";
        List<String> beMember = mdFileReader.readMdFile(beFileName);

        // 크루 리스트 저장
        List<Crew> beMemberCrewList = new ArrayList<>();
        for (String s : beMember) {
            Crew crew = new Crew(Course.BACKEND, s);
            beMemberCrewList.add(crew);
        }
        memberCrewMap.put(Course.BACKEND, beMemberCrewList);

        // 단순 백엔드 멤버 저장
        memberListMap.put("백엔드", beMember);

//            2. 프론트 읽어오기
        String feFileName = "src/main/resources/backend-crew.md";
        List<String> feMember = mdFileReader.readMdFile(feFileName);

        // 크루 리스트 저장
        List<Crew> feMemberCrewList = new ArrayList<>();
        for (String s : feMember) {
            Crew crew = new Crew(Course.FRONTEND, s);
            feMemberCrewList.add(crew);
        }
        memberCrewMap.put(Course.FRONTEND, feMemberCrewList);

        // 단순 프론트 멤버 저장
        memberListMap.put("프론트엔드", feMember);
        List<PairInfo> pairInfos = new ArrayList<>();
        while (true) {
//        2. 기능 입력 받기
            String command = InputView.readCommand();

            if (command.equals("1")) {
//        3. 페어 매칭 기능
//            1. 과정, 레벨, 미션 입력 받기
                List<String> details = InputView.readDetails();
//            2. 멤버 목록에서 페어 만들기
                String courseName = details.get(0);
                Level level = Level.getLevel(details.get(1));
                String missionName = details.get(2);

                if(!level.isMissionInList(missionName)){
                    System.out.println(ErrorMessage.NO_MISSION.getMessage());
                    continue;
                }

                List<List<String>> prePairList = new ArrayList<>();
                prePairList = getLists(pairInfos, Course.getCourse(courseName), level, missionName, prePairList);
                if(!prePairList.isEmpty()){
                    String isRewrite = InputView.readRewrite();
                    if(isRewrite.equals("아니오")){
                        continue;
                    }

                }
                // 과정에 해당하는 멤버 List 가져오기
                List<String> memberList = memberListMap.get(courseName);
                List<Crew> crewList = memberCrewMap.get(Course.getCourse(courseName));
                ShuffleService shuffleService = new ShuffleService(shuffle, crewList);
                List<List<String>> pairList = shuffleService.getPairList(level, memberList);


//            3. 해당 미션에 대한 페어 정보 저장해두기
                pairInfos.add(new PairInfo(Course.getCourse(courseName), level, missionName, pairList));
                OutputView.printPairList(pairList);
            }
//        4. 페어 조회 기능
            else if (command.equals("2")) {
//            1. 과정, 레벨, 미션 입력 받기
                List<String> details = InputView.readDetails();
                Course course = Course.getCourse(details.get(0));
                Level level = Level.getLevel(details.get(1));
                String missionName = details.get(2);
//            2. 과정, 레벨, 미션에 대한 페어 정보 찾기
                List<List<String>> pairList = new ArrayList<>();
                pairList = getLists(pairInfos, course, level, missionName, pairList);

//            3. 결과 출력하기
//                1. 페어 정보 출력
                if (pairList.isEmpty()) {
                    System.out.println(ErrorMessage.NO_PAIR_RECORD.getMessage());
                    continue;
                }
                OutputView.printPairList(pairList);
//                2. 없을 경우 에러 메시지 출력

            }
//        5. 페어 초기화 기능
            else if (command.equals("3")) {
//            1. 기존에 존재하던 페어 전부 초기화 하기
                pairInfos.clear();
                System.out.println("초기화 되었습니다.");
            } else if (command.equals("Q")) {
                return;
            }
        }
    }

    private static List<List<String>> getLists(List<PairInfo> pairInfos, Course course, Level level, String missionName,
                                               List<List<String>> pairList) {
        for (PairInfo pairInfo : pairInfos) {

            if (pairInfo.getCourse().equals(course)
                    && pairInfo.getLevel().equals(level)
                    && pairInfo.getMission().equals(missionName)) {
                if (extracted(pairInfo)) {
                    break;
                }
                pairList = pairInfo.getPairList();
            }
        }
        return pairList;
    }

    private static boolean extracted(PairInfo pairInfo) {
        if (pairInfo.getPairList() == null) {
            System.out.println(ErrorMessage.NO_PAIR_RECORD.getMessage());
            return true;
        }
        return false;
    }
}
