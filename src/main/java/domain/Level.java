package domain;

import java.util.List;

public enum Level {
    LEVEL1("레벨1",List.of("자동차경주","로또","숫자야구게임")),
    LEVEL2("레벨2",List.of("장바구니","결제","지하철노선도")),
    LEVEL3("레벨3",List.of("성능개선","배포")),
    LEVEL4("레벨4",List.of()),
    LEVEL5("레벨5",List.of());

    private String name;
    private List<String> missions;

    Level(String name, List<String> missions) {
        this.name = name;
        this.missions = missions;
    }

    public static Level of(String name){
        for (Level level:Level.values()){
            if (level.name.equals(name)){
                return level;
            }
        }
        throw new IllegalArgumentException("[ERROR] 해당 레벨이 존재하지 않습니다.");
    }

    public void validateExistMission(String name){
        if (!missions.contains(name)){
            throw new IllegalArgumentException("[ERROR] 해당 미션이 존재하지 않습니다.");
        }
    }

}