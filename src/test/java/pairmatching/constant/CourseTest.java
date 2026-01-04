package pairmatching.constant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CourseTest {
    @Test
    void 코스_입력_오류() {
        assertThatThrownBy(() -> Course.from("벡엔드"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ErrorMessage.INVALID_COURSE.getErrorMessage());
    }
}