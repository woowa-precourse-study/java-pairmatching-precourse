package pairmatching.util;

import pairmatching.constant.ErrorMessage;

public final class Validator {

    private static final String CONTENTS_FORMAT = "^ *[가-힣]+ *, *[가-힣]+ *, *[가-힣]+ *)*$";
    private static final String NUMBER_FORMAT = "\\d+";

    private Validator() {}

    public static void validateContentsFormat(String readContents) {
        if (!readContents.matches(CONTENTS_FORMAT)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getErrorMessage());
        }
    }
}
