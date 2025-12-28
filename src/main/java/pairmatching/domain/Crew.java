package pairmatching.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Crew {
    private Course course;
    private String name;
    private EnumMap<Level, List<String>> pairRecord;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
        pairRecord = new EnumMap<>(Level.class);
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    public EnumMap<Level, List<String>> getPairRecord() {
        return pairRecord;
    }

    public List<String> getPairRecordList(Level level) {
        return pairRecord.get(level);
    }

    public void updatePairRecord(Level level, String name) {
        List<String> record = pairRecord.get(level);
        if(record == null) {
            record = new ArrayList<String>();
        }
        record.add(name);
        pairRecord.put(level, record);
    }

    public boolean isPaired(Level level,String name) {
        List<String> record = pairRecord.get(level);
        if(record == null) {
//            System.out.println("해당 레벨에 매칭 이력 존재하지 않음");
            return false;
        }
        // 순회 해서 pair에 존재했으면 -> 매칭 전력있으면 true
        for(String member : record) {
            if(member.equals(name)){
                return true;
            }
        }
        return false;
    }
}
