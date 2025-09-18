package view;

import domain.gameUtil.ResultGame;
import domain.ladder.Ladder;
import domain.ladder.LadderLine;
import domain.ladder.LadderRewardRepository;
import domain.user.UsersRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class OutputView {

    private static final String GAME_REULST = "사다리 결과";
    private static final String HORIZON_LINE = " ----- ";
    private static final String VERTICAL_LINE = "|";
    private static final String SPACE = "     ";
    private static final String LADDER_SPACE = "      ";
    private static final String ALL_RESULT = "all";

    private OutputView(){
        throw new AssertionError();
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
    public static void printLadder(Ladder ladder, LadderRewardRepository ladderRewardRepository, UsersRepository usersRepository){
        String names = usersRepository.unmodifiableUsers().stream().map((user)->user.getName()).collect(Collectors.joining(SPACE));
        String ladderResult = ladderRewardRepository.unmodifiableRewards().stream().map(result->result.getReward()).collect(Collectors.joining(SPACE));
        //유저를 출력
        printMessage(names);
        //사다리를 출력
        ladderPrinter(ladder);
        //결과를 출력
        printMessage(ladderResult);


    }
    private static void ladderPrinter(Ladder ladder){
        List<LadderLine> ladderLineList = ladder.unmodifiableListLineList();
        IntStream.range(0, ladder.callLadderHeight()).forEach((height)->{
            String lineText = IntStream.range(0,ladderLineList.size())
                    .mapToObj((i)->VERTICAL_LINE+SPACE)
                    .collect(Collectors.joining(""));
            String linePoint = IntStream.range(0, ladderLineList.size())
                    .mapToObj((i)->{
                        String direction = ladderLineList.get(i).unmodifiablePoints().get(height).getDirection();
                        if(direction.equals("right")){
                            return HORIZON_LINE;
                        }else{
                            return LADDER_SPACE;
                        }
                    })
                    .collect(Collectors.joining(""));
            printMessage(lineText);
            printMessage(linePoint);
            if(height == ladder.callLadderHeight()-1){
               printMessage(lineText);
            }

        });
    }
    public static void ladderResult(ResultGame resultGame,String name){
        Map<String,String> results = resultGame.resultsunmodifiableMap();
        switch (name){
            case ALL_RESULT :{
                String[] names = results.keySet().toArray(String[]::new);
                String output = Arrays.stream(names)
                        .map(currentName -> currentName + " : [" + results.get(currentName) + "]")
                        .collect(Collectors.joining("\n"));
                printMessage(output);
                break;
            }
            default: {
                if(results.containsKey(name)){
                    printMessage(results.get(name));
                }else{
                    throw new IllegalArgumentException("존재하는 이름이 아닙니다.");
                }
                break;
            }
        }
    }
}
