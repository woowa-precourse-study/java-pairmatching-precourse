package pairmatching;

import static camp.nextstep.edu.missionutils.test.Assertions.assertShuffleTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import pairmatching.Application;
import pairmatching.domain.*;

class ApplicationTest extends NsTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 짝수_인원_페어_매칭() {
        assertShuffleTest(
                () -> {
                    run("1", "백엔드, 레벨1, 자동차경주", "Q");
                    assertThat(output()).contains("태웅 : 백호", "치수 : 태섭");
                },
                Arrays.asList("태웅", "백호", "치수", "태섭")
        );
    }

    @Test
    void 없는_미션에_대한_예외_처리() {
        assertSimpleTest(
                () -> {
                    runException("1", "백엔드, 레벨1, 오징어게임");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }


    /**
     * 내가 만든 테스트
     * **/



//    @Test
//    void 페어_초기화_기능() {
//        Machine machine = new Machine();
//        Choice choice = new Choice(Course.BACKEND, Level.LEVEL1,"자동차경주");
//        machine.matching(choice);
//
//        assertThat(machine.getPairs(choice).size()).isGreaterThan(0);
//
////        machine.reset();
//
////        assertThat(machine.getPairs(choice).size()).isEqualTo(0);
//    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}