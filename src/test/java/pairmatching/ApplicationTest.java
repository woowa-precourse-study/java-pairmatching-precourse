package pairmatching;

import static camp.nextstep.edu.missionutils.test.Assertions.assertShuffleTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import pairmatching.Application;
import pairmatching.constant.ErrorMessage;

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
    void 홀수_인원_페어_매칭() {
        assertShuffleTest(
                () -> {
                    run("1", "백엔드, 레벨1, 자동차경주", "Q");
                    assertThat(output()).contains("태웅 : 백호", "치수 : 태섭 : 소연");
                },
                Arrays.asList("태웅", "백호", "치수", "태섭", "소연")
        );
    }

    @Test
    void 없는_미션에_대한_예외_처리() {
        assertSimpleTest(
            () -> {
                runException("1", "백엔드, 레벨1, 오징어게임");
                assertThat(output()).contains(ErrorMessage.INVALID_MISSION.getErrorMessage());
            }
        );
    }

    @Test
    void 레벨에_속하지_않은_미션에_대한_예외_처리() {
        assertSimpleTest(
                () -> {
                    runException("1", "백엔드, 레벨1, 장바구니");
                    assertThat(output()).contains(ErrorMessage.INVALID_MISSION.getErrorMessage());
                }
        );
    }

    @Test
    void 매칭_실패() {
        assertShuffleTest(
                () -> {
                    runException("1", "백엔드, 레벨1, 자동차경주", "1", "백엔드, 레벨1, 로또");
                    assertThat(output()).contains(ErrorMessage.MATCHING_FAIL.getErrorMessage());
                },
                Arrays.asList("태웅", "백호", "치수", "태섭", "소연"),
                Arrays.asList("태웅", "백호", "치수", "태섭", "소연", "태웅", "백호", "치수", "태섭", "소연", "태웅", "백호", "치수", "태섭", "소연")
        );
    }

    @Test
    void 서로_다른_과정_매칭_성공() {
        assertShuffleTest(
                () -> {
                    run("1", "백엔드, 레벨1, 자동차경주", "1", "프론트엔드, 레벨1, 자동차경주", "Q");
                    assertThat(output()).contains("태웅 : 백호", "치수 : 태섭 : 소연", "보노 : 시저", "쉐리 : 신디");
                },
                Arrays.asList("태웅", "백호", "치수", "태섭", "소연"),
                Arrays.asList("보노", "시저", "쉐리", "신디")
        );
    }

    @Test
    void 재매칭() {
        assertShuffleTest(
                () -> {
                    run("1", "백엔드, 레벨1, 자동차경주", "1", "백엔드, 레벨1, 자동차경주", "네", "Q");
                    assertThat(output()).contains("태웅 : 백호", "치수 : 태섭 : 소연", "백호 : 태섭", "치수 : 태웅 : 소연");
                },
                Arrays.asList("태웅", "백호", "치수", "태섭", "소연"),
                Arrays.asList("백호", "태섭", "치수", "태웅", "소연")
        );
    }

    @Test
    void 기능_입력_오류() {
        assertSimpleTest(
                () -> {
                    runException("4");
                    assertThat(output()).contains(ErrorMessage.INVALID_FORMAT.getErrorMessage());
                }
        );
    }

    @Test
    void 과정_입력_오류() {
        assertSimpleTest(
                () -> {
                    runException("1", "벡엔드, 레벨1, 로또");
                    assertThat(output()).contains(ErrorMessage.INVALID_COURSE.getErrorMessage());
                }
        );
    }

    @Test
    void 레벨_입력_오류() {
        assertSimpleTest(
                () -> {
                    runException("1", "백엔드, 레벨6, 로또");
                    assertThat(output()).contains(ErrorMessage.INVALID_LEVEL.getErrorMessage());
                }
        );
    }

    @Test
    void 없는_입력_오류() {
        assertSimpleTest(
                () -> {
                    runException("1", "백엔드, 레벨1, 줄다리기");
                    assertThat(output()).contains(ErrorMessage.INVALID_MISSION.getErrorMessage());
                }
        );
    }

    @Test
    void 레벨에_맞지_않는_미션_입력_오류() {
        assertSimpleTest(
                () -> {
                    runException("1", "백엔드, 레벨1, 배포");
                    assertThat(output()).contains(ErrorMessage.INVALID_MISSION.getErrorMessage());
                }
        );
    }

    @Test
    void 조회_실패() {
        assertSimpleTest(
                () -> {
                    runException("2", "백엔드, 레벨1, 로또");
                    assertThat(output()).contains(ErrorMessage.NO_EXIST_MATCHING_RESULT.getErrorMessage());
                }
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
