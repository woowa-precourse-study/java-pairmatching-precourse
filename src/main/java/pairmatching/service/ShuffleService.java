package pairmatching.service;

import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.generator.RandomGenerator;

public class ShuffleService {
    private RandomGenerator randomGenerator;
    private List<Crew> crewList;

    public ShuffleService(RandomGenerator randomGenerator, List<Crew> crewList) {
        this.randomGenerator = randomGenerator;
        this.crewList = crewList;
    }


    public List<List<String>> getPairList(Level level, List<String> memberList){
        List<List<String>> pairList = new ArrayList<>();

        //원본 리스트
        List<String> shuffledList = randomGenerator.getShuffledList(memberList);

        // 결과 저장 List
        while(!shuffledList.isEmpty()){
            List<String> result = new ArrayList<>();
            String member1 = shuffledList.get(0);
            String member2 = shuffledList.get(1);
            // 매칭 전적이 있으면 다시 섞음
            if(isMatched(level, member1, member2)) {
                shuffledList = randomGenerator.getShuffledList(shuffledList);
                continue;
            }
            // 매칭 전적 저장
            updatePair(level,member1);
            updatePair(level,member2);

            // 결과 저장 리스트에 저장
            result.add(member1);
            result.add(member2);

            // 원본 리스트에서 삭제
//            shuffledList.remove(member1);
//            shuffledList.remove(member2);
                // 위 코드 안되면 아래로 두번 지우기
              shuffledList.remove(0);
              shuffledList.remove(0);

            // <member, member>를 저장
            pairList.add(result);
        }
        return pairList;
    }

    public boolean isMatched(Level level, String member1, String member2) {
        for (Crew crew : crewList) {
            if (crew.getName().equals(member1)) {
                return crew.isPaired(level,member2);
            }
        }
        return false;
    }

    public void updatePair(Level level, String member) {
        for (Crew crew : crewList) {
            if (crew.getName().equals(member)) {
                crew.updatePairRecord(level, member);
            }
        }
    }
}
