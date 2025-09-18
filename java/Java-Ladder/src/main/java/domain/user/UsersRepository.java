package domain.user;

import domain.gameUtil.ResetUtill;
import domain.user.constans.UserConstansMesseges;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UsersRepository implements ResetUtill {
    //자신은 참조 시켜 인스턴스를 생성하지 않았을경우 다시 생성
    private static UsersRepository instance;
    //생성시 참조값이 변하지 않도록 final선언 참조대상 내부의 값은 수정 가능
    private final List<User> users;
    //싱글톤 패턴 적용
    private UsersRepository(List<User> users) {
        this.users = users;
    }

    public static UsersRepository createUsers(String inputUsers) {
        if(inputUsers.isEmpty()){
            throw new IllegalArgumentException("이름을 입력해주세요");
        }
        if(instance==null) {
            instance = new UsersRepository(Arrays.stream(inputUsers.split(","))
                    .map(User::new)
                    .collect(Collectors.toList()));
        }
        return instance;
    }

    public User userByIndex(int index) {
        return users.get(index);
    }
    // 수정이 불가능 하도록 readOnly배열 반환
    public List<User> unmodifiableUsers() {
        return Collections.unmodifiableList(users);
    }

    public void userSwap(int prevIndex, int currentIndex) {
        Collections.swap(users, prevIndex, currentIndex);
    }

    public void changeUserNm(int userIndex, String name){
        if(userIndex < 0 || users.size() < userIndex){
            throw new IllegalArgumentException(UserConstansMesseges.ILLEGAL_USERS_SIZE.getMessage());
        }
        users.get(userIndex).setName(name);
    }

    public int countOfUser() {
        return users.size();
    }

    @Override
    public void resetInstance() {
        UsersRepository.instance = null;
    }
}
