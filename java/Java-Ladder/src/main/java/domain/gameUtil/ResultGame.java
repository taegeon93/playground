package domain.gameUtil;

import domain.ladder.Ladder;
import domain.ladder.LadderLine;
import domain.ladder.LadderReward;
import domain.user.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResultGame implements ResetUtill{

    private static  ResultGame instance;

    private final HashMap<String,String> results;

    private ResultGame(HashMap<String,String> results){
        validateResultGame(results);
        this.results = results;
    }

    public static ResultGame createResultMap(Ladder ladder, List<LadderReward> ladderRewards, List<User> users){
        if(instance == null){
            HashMap<String,String> results = new HashMap<>();

            List<LadderLine> ladderLines = ladder.unmodifiableListLineList();
            int ladderHeight = ladder.callLadderHeight();

            for (int idx = 0; idx < users.size(); idx++) {
                int currentPosition = idx;

                for (int height = 0; height < ladderHeight; height++) {
                    String direction = ladderLines.get(currentPosition)
                            .unmodifiablePoints()
                            .get(height)
                            .getDirection();

                    switch (direction) {
                        case "right":
                            currentPosition++;
                            break;
                        case "left":
                            currentPosition--;
                            break;
                    }
                }
                results.put(users.get(idx).getName(),ladderRewards.get(currentPosition).getReward());
            }
            return new ResultGame(results);
        }
        return instance;
    }

    /**
     * 게임결과를 저장하는 객체 이름과 결과를 한쌍으로 저장함
     * @param results
     */
    private void validateResultGame(HashMap<String,String> results){
        if(results.isEmpty()){
            throw new IllegalArgumentException("결과가 없습니다.");
        }
    }
    public Map<String,String> resultsunmodifiableMap(){
       return Collections.unmodifiableMap(results);
    }

    @Override
    public void resetInstance() {
        ResultGame.instance = null;
    }
}
