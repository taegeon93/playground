package domain.ladder;

import domain.gameUtil.DirectionMoveStrategy;
import domain.ladder.constans.LadderConstansMesseges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

//사다리 라인 클래스
public class LadderLine {

    private static final int LADDER_MIN_POSITION = 0;

    private static final int LADDER_MIN_HEIGHT = 1;

    private int position;

    private List<Point> points;


    private LadderLine(int position, List<Point> points){
        this.position = position;
        this.points = points;
    }
    /**
     * 사다리 라인생성 메소드
     * @param ladderHeight 사다리 높이
     * @param position
     * @return 사다리 라인하나를 리턴함
     */
    public static LadderLine createLadderLine(int ladderHeight, int position,int countOfUser, List<LadderLine> ladderLineList){
        //사다리 높이 체크
        if(ladderHeight < LADDER_MIN_HEIGHT){
            throw new IllegalArgumentException(LadderConstansMesseges.ILLEGAL_HEIGHT_MESSAGE.getMessage());
        }
        //사다리 포지션 체크
        if(position < LADDER_MIN_POSITION){
            throw new IllegalArgumentException(LadderConstansMesseges.ILLEGAL_POSISION_MESSAGE.getMessage());
        }
        DirectionMoveStrategy moveStrategy = new DirectionMoveStrategy();
        //point를 생성하는 함수 필요
        List<Point> points = new ArrayList<>();
        if(position==0){
            IntStream.range(0,ladderHeight).forEach((i)->{
                points.add(new Point(false, moveStrategy.movable()));
            });
        }else if(position + 1 == countOfUser ){
            List<Point> prevLinePoints = ladderLineList.get(position-1).unmodifiablePoints();
            prevLinePoints.stream().forEach((i)->{
                if(i.getDirection() == "right"){
                    points.add(new Point(true,false));
                }else{
                    points.add(new Point(false, false));
                }
            });
        }else{
            List<Point> prevLinePoints = ladderLineList.get(position-1).unmodifiablePoints();
            prevLinePoints.stream().forEach((i)->{
                if(i.getDirection() == "right"){
                    points.add(new Point(true,false));
                }else{
                    points.add(new Point(false, moveStrategy.movable()));
                }
            });
        }

        return new LadderLine(position, points);
    }

    public int getLinePosition(){
        return this.position;
    }

    public List<Point> unmodifiablePoints(){
        return Collections.unmodifiableList(points);
    }
}
