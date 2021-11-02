package ca.uqac.game;

public class PigeonFood {
    private int xPosition;
    private int yPostion;
    private boolean isRotten = false;

    public PigeonFood(int xPosition, int yPostion) {
        this.xPosition = xPosition;
        this.yPostion = yPostion;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPostion() {
        return yPostion;
    }

    public void setyPostion(int yPostion) {
        this.yPostion = yPostion;
    }

    public boolean isRotten() {
        return isRotten;
    }

    public void setRotten(){
        this.isRotten = true;
    }
}
