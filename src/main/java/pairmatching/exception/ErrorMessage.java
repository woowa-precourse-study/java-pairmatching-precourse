package pairmatching.exception;

public enum ErrorMessage{
    INVALID_INPUT("입력 값이 올바르지 않습니다."),
    INVALID_TYPE("형식이 올바르지 않습니다."),
    NO_PAIR_RECORD("매칭 이력이 없습니다."),
    NO_MISSION("미션이 존재하지 않습니다.")
    ;

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(){
        String prefix = "[ERROR] ";
        return prefix + message;
    }
}