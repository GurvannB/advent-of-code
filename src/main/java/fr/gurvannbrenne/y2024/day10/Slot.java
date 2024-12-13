package fr.gurvannbrenne.y2024.day10;

public class Slot {
    private int x;
    private int y;
    private int height;

    public Slot(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isTrailHead() {
        return height == 0;
    }

    public String toString() {
        return "(x="+x+";y="+y+";h="+height+")";
    }
}
