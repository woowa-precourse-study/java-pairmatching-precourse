package pairmatching.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import pairmatching.constant.ErrorMessage;

class MenuOptionTest {
    @Test
    void 기능_입력_오류() {
        assertThatThrownBy(() -> MenuOption.from("4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_FORMAT.getErrorMessage());
    }

    @Test
    void 기능_입력_정상_작동() {
        MenuOption option = MenuOption.from("3");
        assertThat(option).isEqualTo(MenuOption.C);
    }
}