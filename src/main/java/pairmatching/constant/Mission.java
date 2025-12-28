package pairmatching.constant;

import java.util.Arrays;

public enum Mission {

    RACING_CAR("자동차경주"),
    LOTTO("로또"),
    BASEBALL("숫자야구게임"),
    SHOPPING_BAG("장바구니"),
    PAYMENT("결제"),
    SUBWAY("지하철노선도"),
    IMPROVEMENT("성능개선"),
    DEPLOYMENT("배포"),
    ;

    private String name;

    Mission(String name) {
        this.name = name;
    }

    public static Mission from(String missionName) {
        return Arrays.stream(values())
                .filter(mission -> mission.name.equals(missionName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_MISSION.getErrorMessage()));
    }
}
