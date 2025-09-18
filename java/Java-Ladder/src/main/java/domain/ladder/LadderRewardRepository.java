package domain.ladder;

import domain.gameUtil.ResetUtill;
import domain.ladder.constans.LadderConstansMesseges;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LadderRewardRepository implements ResetUtill {


    private static LadderRewardRepository instance;

    private final List<LadderReward> rewards;

    private LadderRewardRepository(List<LadderReward> rewards){
        this.rewards = rewards;
    }

    public static LadderRewardRepository createRewards(String inputRewards, int countOfUsers){
        if(inputRewards.isEmpty()|| inputRewards.split(",").length != countOfUsers){
            throw new IllegalArgumentException(LadderConstansMesseges.ILLEGAL_COUNT_MESSAGE.getMessage());
        }
        if(instance==null){
            instance = new LadderRewardRepository(Arrays.stream(inputRewards.split(","))
                    .map(reward-> LadderReward.builder()
                            .reward(reward)
                            .build())
                    .collect(Collectors.toList()));
        }

        return instance;
    }
    public List<LadderReward> unmodifiableRewards(){
        return Collections.unmodifiableList(rewards);
    }

    public void changeRewardNm(int rewardIndex, String reward){
        if(rewardIndex < 0 || rewards.size() < rewardIndex){
            throw new IllegalArgumentException(LadderConstansMesseges.ILLEGAL_REWARDS_SIZE.getMessage());
        }
        rewards.get(rewardIndex).setReward(reward);
    }
    @Override
    public void resetInstance(){
        LadderRewardRepository.instance = null;
    }


}
