package view;

import domain.gameUtil.ResultGame;
import domain.ladder.Ladder;
import domain.ladder.LadderRewardRepository;
import domain.user.UsersRepository;

import java.util.Scanner;

import static view.OutputView.ladderResult;
import static view.OutputView.printMessage;

public class InputView {

    private static final String DEMAND_NAMES = "참여할 사람 이름을 입력하세요. (이름은 , 구분)";
    private static final String DEMAND_RESULT_ITEMS = "실행 결과를 입력하세요. (결과는 , 구분)";
    private static final String DEMAND_LADDER_HEIGHT = "최대 사다리 높이를 입력해주세요.";
    private static final String DEMAND_SEARCH_TARGET = "결과를 보고 싶은 사람의 이름을 입력하거나 all을 입력하세요.";
    private static final String DEMAND_LADDER_RESET = "사다리를 초기화 하시겠습니까? (Y/N)";
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView(){
        throw new AssertionError();
    }

    public static UsersRepository inputPlayerName() {
        printMessage(DEMAND_NAMES);

        return UsersRepository.createUsers(SCANNER.nextLine());
    }
    public static LadderRewardRepository inputRewards(int countOfUsers) {
        printMessage(DEMAND_RESULT_ITEMS);

        return LadderRewardRepository.createRewards(SCANNER.nextLine(),countOfUsers);
    }

    public static Ladder inputLadderHeight(int countOfUsers){
        printMessage(DEMAND_LADDER_HEIGHT);

        String stringladderHeight = SCANNER.nextLine();
        try{
            int ladderHeight = Integer.parseInt(stringladderHeight);
            return Ladder.createLadder(countOfUsers, ladderHeight);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.");
        }
    }

    public static void inputResult(ResultGame resultGame){
        printMessage(DEMAND_SEARCH_TARGET);
        ladderResult(resultGame, SCANNER.nextLine());
    }
    public static boolean inputReset(){
        printMessage(DEMAND_LADDER_RESET);
        String yn = SCANNER.nextLine();
        switch (yn){
            case "Y" :
                return true;
            case "N" :
                return false;
            default:
                throw new IllegalArgumentException("Y,N 만을 입력해주세요.");
        }
    }


}
