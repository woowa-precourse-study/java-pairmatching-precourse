package pairmatching.constant;

public enum ErrorMessage {

    INVALID_FORMAT("잘못된 형식을 입력했습니다."),
    INVALID_COURSE("잘못된 과정을 입력했습니다."),
    INVALID_LEVEL("잘못된 과정을 입력했습니다."),
    INVALID_MISSION("잘못된 미션을 입력했습니다."),
    NO_EXIST_MATCHING_RESULT("매칭 이력이 없습니다.");

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(Object... args) {
        return ERROR_MESSAGE_PREFIX + String.format(errorMessage, args);
    }
}
