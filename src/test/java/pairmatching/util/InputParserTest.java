package pairmatching.util;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @Test
    void 입력_파싱_테스트() {
        //given
        String input = "프론트엔드, 레벨1, 자동차경주";
        List<String> result = List.of("프론트엔드", "레벨1", "자동차경주");
        //when
        List<String> parse = InputParser.parse(input);
        //then
        Assertions.assertThat(parse)
                .hasSize(3)
                .containsAll(result);
    }
}
