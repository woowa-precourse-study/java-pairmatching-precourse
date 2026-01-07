//package pairmatching.domain;
//
//import pairmatching.domain.Course;
//import pairmatching.domain.Level;
//import pairmatching.domain.PairGroup;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class PairHistory {
//    private final Level level;
//    private final Course course;
//    private final Set<PairGroup> pairGroups=new HashSet<>();
//
//    public PairHistory(Level level,Course course){
//        this.level=level;
//        this.course=course;
//    }
//
//    public void addPairGroups(Set<PairGroup> groups){
//        pairGroups.addAll(groups);
//    }
//
//    public void validateUniqueGroup(PairGroup groups){
//        if (pairGroups.contains(groups)){
//            throw new IllegalArgumentException("같은 레벨에 중복인 매칭이 있습니다.");
//        }
//    }
//}
