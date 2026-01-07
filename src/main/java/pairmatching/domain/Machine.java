package pairmatching.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Machine {
    private final EnumMap <Course, CrewGroup> groups=new EnumMap<>(Course.class);
    private final List<Result> results=new ArrayList<>();

    public Machine() {
        for (Course course:Course.values()){
            groups.put(course,new CrewGroup());
        }
    }

    public boolean isMatchedAlready(Choice choice){
        for (Result result:results){
            if (result.hasHistory(choice)){
                return true;
            }
        }
        return false;
    }

    public void matching(Choice choice){


    }

}
