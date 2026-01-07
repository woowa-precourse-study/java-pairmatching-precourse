package pairmatching.domain;

import java.util.*;

public class Machine {
    private final EnumMap <Course, CrewGroup> groups=new EnumMap<>(Course.class);
    private final Map<Choice,Result> results=new HashMap<>();

    public Machine() {
        for (Course course:Course.values()){
            groups.put(course,new CrewGroup());
        }
    }

    public void add(Course course,CrewGroup crewGroup){
        groups.put(course,crewGroup);
    }

//    public boolean isMatchedAlready(Choice choice){
//        for (Result result:results){
//            if (result.hasHistory(choice)){
//                return true;
//            }
//        }
//        return false;
//    }

    public void matching(Choice choice){
        CrewGroup crewGroup = groups.get(choice.getCourse());
        crewGroup.match(choice.getLevel());
        List<Pair> pairs = crewGroup.getPairs(choice.getLevel());
        saveResult(choice,pairs);
    }

    public void saveResult(Choice choice,List<Pair> pairs){
        results.put(choice,new Result(pairs));
    }

    public List<Pair> getPairs(Choice choice){
        try{
            return results.get(choice).getPairs();
        } catch (NullPointerException e){
            throw new IllegalArgumentException("[ERROR] 매칭 이력이 없습니다.");
        }
    }

    public void reset(){
        results.clear();
    }

}
