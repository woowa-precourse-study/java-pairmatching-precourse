package pairmatching.domain.vo;

import java.util.Arrays;
import pairmatching.exception.ErrorCode;

public enum Mission {
    CAR_RACING("자동차경주"),
    LOTTO("로또"),
    NUMBER_BASEBALL("숫자야구게임"),
    CART("장바구니"),
    PAY("결제"),
    SUBWAY("지하철노선도"),
    IMPROVE("성능개선"),
    DEPLOY("배포");

    private String name;

    Mission(String name) {
        this.name = name;
    }

    public static Mission of(String missionValue) {
        return Arrays.stream(values())
                .filter(mission -> mission.name.equals(missionValue))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NOT_VALID_MISSION.getMessage()));
    }
}
