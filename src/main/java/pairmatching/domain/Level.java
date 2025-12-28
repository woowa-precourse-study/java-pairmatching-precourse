package pairmatching.domain;

import java.util.*;

public enum Level {
    LEVEL_ONE("레벨1", List.of("자동차경주", "로또", "숫자야구게임")),
    LEVEL_TWO("레벨2", List.of("장바구니", "결제", "지하철노선도")),
    LEVEL_THREE("레벨3", List.of()),
    LEVEL_FOUR("레벨4", List.of("성능개선", "배포")),
    LEVEL_FIVE("레벨5", List.of());

    private final String level;
    private final List<String> missions;


    Level(String level, List<String> missions) {
        this.level = level;
        this.missions = missions;
    }

    public static Level fromLevel(String korLevel) {
        for (Level level : Level.values()) {
            if (level.level.equals(korLevel)) {
                return level;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 카테고리");
    }

    public void validateMission(String name) {
        if (!missions.contains(name)){
            throw new IllegalArgumentException("존재하지 않는 카테고리");
        }
    }



}

