package domain.user;

import domain.user.constans.UserConstansMesseges;
import lombok.Getter;
import lombok.Setter;

@Getter
public class User {
    //이름의 최대 길이
    private static int NAME_LENGTH = 5;

    private String name;

    public User(String name){
        validateUserName(name);
        this.name = name;
    }

    private static void validateUserName(String name) {
        if (name.length() > NAME_LENGTH || name.isEmpty()) {
            throw new IllegalArgumentException(UserConstansMesseges.ILLEGAL_NAME_MESSAGE.getMessage() + name);
        }
    }
    public void setName(String name) {
        validateUserName(name);
        this.name = name;
    }
}
