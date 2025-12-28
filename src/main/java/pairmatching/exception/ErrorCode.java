package pairmatching.exception;

public enum ErrorCode {
    NOT_VALID_COMMAND("유효한 기능이 아닙니다."),
    NOT_VALID_COURSE("코스의 이름이 정확하지 않습니다."),
    NOT_VALID_LEVEL("레벨의 이름이 정확하지 않습니다."),
    NOT_VALID_COURSE_AND_MISSION("정확한 코스와 미션을 입력해주세요"),
    NOT_VALID_MISSION("미션의 이름이 정확하지 않습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
