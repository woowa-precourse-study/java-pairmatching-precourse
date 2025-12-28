package pairmatching.domain;

import java.util.*;

public enum Mission {
    LEVEL_ONE("레벨1", List.of("자동차경주", "로또", "숫자야구게임")),
    LEVEL_TWO("레벨2", List.of("장바구니", "결제", "지하철노선도")),
    LEVEL_THREE("레벨3", List.of()),
    LEVEL_FOUR("레벨4", List.of("성능개선", "배포")),
    LEVEL_FIVE("레벨5", List.of());

    private final String level;
    private final List<String> missions;


    Mission(String level, List<String> missions) {
        this.level = level;
        this.missions = missions;
    }

    public static Mission fromLevel(String korLevel) {
        for (Mission mission : Mission.values()) {
            if (mission.level.equals(korLevel)) {
                return mission;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 카테고리");
    }

}

