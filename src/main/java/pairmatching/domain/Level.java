package pairmatching.domain;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import java.util.Arrays;
import java.util.List;

public enum Level {
    LEVEL1("레벨1", Arrays.asList("자동차경주", "로또", "숫자야구게임")),
    LEVEL2("레벨2", Arrays.asList("장바구니","결제","지하철노선도")),
    LEVEL3("레벨3",Arrays.asList("")),
    LEVEL4("레벨4",Arrays.asList("성능개선","배포")),
    LEVEL5("레벨5",Arrays.asList(""));

    private String name;
    private List<String> misssion;
    Level(String name,List<String> misssion) {
        this.name = name;
        this.misssion = misssion;

    }

    public static Level getLevel(String name) {
        for (Level level : Level.values()) {
            if (level.name.equals(name)) {
                return level;
            }
        }
        throw new IllegalArgumentException("잘못된 level");
    }
    public List<String> getMission() {
        return misssion;
    }

    public boolean isMissionInList(String mission){
        for(Level level : Level.values()){
            if(level.misssion.contains(mission)){
                return true;
            }
        }
        return false;
    }

    // 추가 기능 구현
}