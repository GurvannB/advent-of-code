package fr.gurvannbrenne.y2024.day06;

public enum Direction {
    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

    public Integer vectorX;
    public Integer vectorY;
    public Direction nextIfCollide;

    static {
        UP.nextIfCollide = RIGHT;
        DOWN.nextIfCollide = LEFT;
        LEFT.nextIfCollide = UP;
        RIGHT.nextIfCollide = DOWN;
    }

    Direction(Integer vectorX, Integer vectorY) {
        this.vectorX = vectorX;
        this.vectorY = vectorY;
    }
}
