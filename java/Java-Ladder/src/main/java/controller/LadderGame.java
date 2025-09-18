package controller;

import domain.gameUtil.ResultGame;
import domain.ladder.Ladder;
import domain.ladder.LadderRewardRepository;
import domain.ladder.constans.LadderConstansMesseges;
import domain.user.UsersRepository;

import static view.InputView.*;
import static view.OutputView.printLadder;
import static view.OutputView.printMessage;

public class LadderGame {

    public void GameRun(){
        while (true) {
            // user정보 생서
            UsersRepository usersRepository = promptPlayerNames();
            // 결과 생성
            LadderRewardRepository ladderRewardRepository = promptRewards(usersRepository.countOfUser());
            //사다리 높이를 받아 사다리를 생성
            Ladder ladder = promptladderHeight(usersRepository.countOfUser());
            //사다리 그리기
            printLadder(ladder, ladderRewardRepository, usersRepository);
            //결과 받을 형식
            ResultGame results = ResultGame.createResultMap(ladder, ladderRewardRepository.unmodifiableRewards(), usersRepository.unmodifiableUsers());

            boolean ladderReset = false;

            while (!ladderReset) {
                inputResult(results);
                ladderReset = promptReset();
                //true일 경우 리셋
                if (ladderReset) {
                    usersRepository.resetInstance();
                    ladderRewardRepository.resetInstance();
                    ladder.resetInstance();
                    results.resetInstance();
                }
            }

        }

    }
    //user 입력이 올바르지 않을경우를 위한 재귀 함수
    private UsersRepository promptPlayerNames() {
        try {
            return inputPlayerName();
        } catch (IllegalArgumentException e) {
            printMessage("에러: " + e.getMessage());
            printMessage("다시 입력해주세요.\n");
            return promptPlayerNames();
        }
    }

    private LadderRewardRepository promptRewards(int countOfUser){
        try {
            return inputRewards(countOfUser);
        }catch (IllegalArgumentException e){
            printMessage(LadderConstansMesseges.ILLEGAL_COUNT_MESSAGE.getMessage());
            return promptRewards(countOfUser);
        }
    }
    private Ladder promptladderHeight(int countOfUser){
        try {
            return inputLadderHeight(countOfUser);
        }catch (IllegalArgumentException e){
            printMessage("에러: " + e.getMessage());
            return promptladderHeight(countOfUser);
        }
    }

    private boolean promptReset(){
        try {
            return inputReset();
        }catch (IllegalArgumentException e){
            printMessage(e.getMessage());
            return promptReset();
        }
    }
}
