package pairmatching.command;

import java.util.Arrays;
import pairmatching.constant.ErrorMessage;

public enum MenuOption {
    A("1"),
    B("2"),
    C("3"),
    QUIT("Q");

    private final String code;

    MenuOption(String code) {
        this.code = code;
    }

    public static MenuOption from(String command) {
        String normalized = command.trim();
        return Arrays.stream(values())
                .filter(opt -> opt.code.equals(normalized))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getErrorMessage()));
    }
}
