package domain.ladder;

import domain.ladder.constans.LadderConstansMesseges;

//사다리에 존재하는 이동포인트
public class Point {

    private boolean left;

    private boolean right;

    public Point (boolean left, boolean right){
        validatePoint(left, right);
        this.left = left;
        this.right = right;
    }

    private void validatePoint(boolean left, boolean right) {
        if (left && right) {
            throw new IllegalArgumentException(LadderConstansMesseges.ILLEGAL_DIRECTION_MESSAGE.getMessage());
        }
    }

    //해당 포인트의 방향을 반환하는 메서드
    public String getDirection(){
        if(this.left){
            return "left";
        }else if(this.right){
            return "right";
        }else{
            return "south";
        }

    }

}
