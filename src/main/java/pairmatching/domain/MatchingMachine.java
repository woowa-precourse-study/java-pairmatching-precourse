package pairmatching.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MatchingMachine {
    private final Map<Options,List<Pairs>> matchingMachine=new LinkedHashMap<>();

    public MatchingMachine() {
    }

    public void addMatch(Options options, List<Pairs> pairs) {
        // TODO : 검증 추가
        this.matchingMachine.put(options,pairs);
    }

    public List<Pairs> getMatch(Options options) {
        return matchingMachine.get(options);
    }

    // 매칭 정보가 있는지 확인
    public boolean isAlreadyMatched(Options options){
        if (matchingMachine.containsKey(options)){
            return true;
        }
        return false;
    }

    public void reset(){
        matchingMachine.clear();
    }


}
