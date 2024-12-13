package fr.gurvannbrenne.y2015.day03;

import java.util.Arrays;

public enum Direction {
    UP('^', 0, -1), RIGHT('>', 1, 0), BOTTOM('v', 0, 1), LEFT('<', -1, 0);

    public final char direction;
    public final int dx;
    public final int dy;

    Direction(char direction, int dx, int dy) {
        this.direction = direction;
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction getPositionByDirection(char c) {
        return Arrays.stream(Direction.values()).filter((pos) -> pos.direction == c).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
