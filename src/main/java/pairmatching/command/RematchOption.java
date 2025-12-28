package pairmatching.command;

import java.util.Arrays;
import pairmatching.constant.ErrorMessage;

public enum RematchOption {
    YES("네"),
    NO("아니오"),
    ;

    private final String name;

    RematchOption(String name) {
        this.name = name;
    }

    public static RematchOption from(String readOpt) {
        return Arrays.stream(values())
                .filter(opt -> opt.name.equals(readOpt.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getErrorMessage()));
    }
}
