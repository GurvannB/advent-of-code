package fr.gurvannbrenne.y2024.day06;

public class Slot {
    private boolean isWall;
    public int x;
    public int y;

    public Slot(boolean isWall, int x, int y) {
        this.isWall = isWall;
        this.x = x;
        this.y = y;
    }

    public boolean getIsWall() {
        return isWall;
    }

    public void setIsWall(boolean isWall) {
        this.isWall = isWall;
    }
}
