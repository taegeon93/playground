package domain.user.constans;

public enum UserConstansMesseges {

    ILLEGAL_NAME_MESSAGE("이름은 최대 5글자까지 부여할 수 있습니다. 현재 이름 : "),
    ILLEGAL_USERS_SIZE("0 또는 유저수를 초과 할 수 없습니다.");

    private final String message;

    UserConstansMesseges(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
