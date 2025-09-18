package domain.ladder;


import domain.gameUtil.ResetUtill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Ladder implements ResetUtill {

    private static Ladder instance;

    private final List<LadderLine> ladderLineList;

    private int ladderHeight;

    private Ladder(List<LadderLine> ladderLineList, int ladderHeight){
        this.ladderLineList = ladderLineList;
        this.ladderHeight = ladderHeight;
    }

    public static Ladder createLadder(int countOfUser, int currentladderHeight){

        if( instance == null ) {
            List<LadderLine> ladderLineList = new ArrayList<>();
            IntStream.range(0, countOfUser).forEach((i) -> {
                ladderLineList.add(LadderLine.createLadderLine(currentladderHeight, i, countOfUser, ladderLineList));
            });
            instance = new Ladder(ladderLineList, currentladderHeight);
        }

        return instance;
    }
    public List<LadderLine> unmodifiableListLineList(){
        return Collections.unmodifiableList(ladderLineList);
    }
    public int callLadderHeight() {
        return ladderHeight;
    }

    @Override
    public void resetInstance() {
        Ladder.instance = null;
    }
}
