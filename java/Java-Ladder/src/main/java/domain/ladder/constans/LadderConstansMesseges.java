package domain.ladder.constans;

public enum LadderConstansMesseges {

    ILLEGAL_COUNT_MESSAGE("유저와 경품 수가 같아야 합니다."),
    ILLEGAL_REWARDS_SIZE("경품 수량을 초과했습니다."),
    ILLEGAL_DIRECTION_MESSAGE("라인이 겹칠 수 없습니다."),
    ILLEGAL_HEIGHT_MESSAGE("사다리 높이는 1보다 작을 수 없습니다."),
    ILLEGAL_POSISION_MESSAGE("사다리 위치는 음수일 수 없습니다.");

    private final String message;

    LadderConstansMesseges(String message){
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
