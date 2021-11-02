package ca.uqac.game;

import java.util.List;

public class Pigeon {
    private int positionX;
    private int positionY;
    private final Board board;
    private Thread piegonThread;
    private static final int speed  = 10;

    public Pigeon(int positionX, int positionY, Board board) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.board = board;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    private void move() throws InterruptedException {
        while (true) {
            synchronized (this.board) {
                PigeonFood closestFood = this.getClosestFood(this.board);
                if (this.getPositionY() == closestFood.getyPostion() && this.getPositionX() == closestFood.getxPosition()){
                    this.board.getPigeonFoods().remove(closestFood);
                    System.out.println("Food eaten");
                }
                else {
                    this.moveToward(closestFood.getxPosition(),closestFood.getyPostion());
                }
                this.piegonThread.sleep(1000);
            }
        }
    }

    private PigeonFood getClosestFood(Board board) {
        PigeonFood result = null;
        int pigeonDistance = -1;
        for (PigeonFood pigeonFood :
                board.getPigeonFoods()) {
            if (pigeonFood.isRotten()){
                continue;
            }
            int distanceFoodY = this.computeFoodDistance(pigeonFood.getyPostion(), this.getPositionY());
            int distanceFoodX = this.computeFoodDistance(pigeonFood.getxPosition(), this.getPositionX());
            pigeonDistance = distanceFoodX + distanceFoodY;
            if (pigeonDistance == -1) {
                result = pigeonFood;
                continue;
            }
            if (pigeonDistance > pigeonDistance) {
                result = pigeonFood;
            }
        }
        return result;
    }

    private int computeFoodDistance(int foodPosition, int pigeonPosition) {
        if (pigeonPosition > foodPosition) {
            return pigeonPosition - foodPosition;
        }
        return foodPosition - pigeonPosition;
    }

    private void moveToward(int x, int y){
        int pigeonMoves = Pigeon.speed;
        while (pigeonMoves > 0){
            if (this.positionX > x && this.positionY > y){
                this.positionX--;
                this.positionY--;
                pigeonMoves -=2;
            }
            else if(this.positionX > x && this.positionY < y){
                this.positionX--;
                this.positionY++;
                pigeonMoves -=2;
            }
            else if(this.positionX < x && this.positionY > y){
                this.positionX++;
                this.positionY--;
                pigeonMoves -=2;
            }
            else if (this.positionY < y && this.positionX < x){
                this.positionX++;
                this.positionY++;
                pigeonMoves -=2;
            }
        }
    }

    private void randomMove(){

    }

}
