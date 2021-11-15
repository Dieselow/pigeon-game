package ca.uqac.game;

import java.util.Random;

public class Pigeon implements Runnable {
    private int id;
    private int positionX;
    private int positionY;
    private final Board board;
    private Thread piegonThread;
    private static final int speed = 10;

    public Pigeon(int positionX, int positionY, Board board, int id) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.board = board;
        this.id = id;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.board) {
                Random randomChance = new Random();
                if (this.board.getPigeonFoods().size() > 0) {
                    PigeonFood closestFood = this.getClosestFood(this.board);
                    if (closestFood != null){
                        if (this.getPositionY() == closestFood.getyPostion() && this.getPositionX() == closestFood.getxPosition()) {
                            this.board.removeFood(closestFood);
                            System.out.println("Food eaten by Pigeon " + id);
                        } else {
                            this.moveToward(closestFood.getxPosition(), closestFood.getyPostion());
                        }
                    }
                } else if (randomChance.nextInt(101) < randomChance.nextInt(101)) {
                    this.randomMove();
                }
            }
            try {
                Thread.sleep(1000); // needed outside the loop in order to repaint
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private PigeonFood getClosestFood(Board board) {
        PigeonFood result = null;
        int pigeonDistance = -1;
        for (PigeonFood pigeonFood :
                board.getPigeonFoods()) {
            if (pigeonFood.isRotten()) {
                continue;
            }
            int distanceFoodY = this.computeFoodDistance(pigeonFood.getyPostion(), this.getPositionY());
            int distanceFoodX = this.computeFoodDistance(pigeonFood.getxPosition(), this.getPositionX());
            int foodDistance = distanceFoodX + distanceFoodY;
            if (pigeonDistance == -1) {
                result = pigeonFood;
                pigeonDistance = foodDistance;
                continue;
            }
            if (pigeonDistance > foodDistance) {
                result = pigeonFood;
                pigeonDistance = foodDistance;
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

    private void moveToward(int x, int y) {
        int pigeonMoves = Pigeon.speed;
        while (pigeonMoves > 0) {
            if (this.positionX > x || this.positionY > y) {
                if (this.positionX > x){
                    this.positionX--;
                    pigeonMoves--;
                }
                if (this.positionY > y){
                    this.positionY--;
                    pigeonMoves--;
                }
            } else if (this.positionY < y || this.positionX < x) {
                if (this.positionX < x){
                    this.positionX++;
                    pigeonMoves--;
                }
                if (this.positionY < y){
                    this.positionY++;
                    pigeonMoves--;
                }
            }
            else {
                break;
            }
        }
    }

    public void startANewLife() {
        if (this.piegonThread == null) {
            this.piegonThread = new Thread(this);
            this.piegonThread.start();
        }
    }

    private void randomMove() {
        int randomX = new Random().nextInt(this.board.getSizeX());
        int randomY = new Random().nextInt(this.board.getSizeY());
        this.moveToward(randomX, randomY);
    }

}
