package fr.gurvannbrenne.y2024.day14;

public class Robot {
    int x;
    int y;
    int vx;
    int vy;

    private Robot(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    public static Robot parseLine(String line) {
        String[] parts = line.split(" ");

        String[] position = parts[0].split(",");
        int x = Integer.parseInt(position[0].substring(2));
        int y = Integer.parseInt(position[1]);

        String[] velocities = parts[1].split(",");
        int vx = Integer.parseInt(velocities[0].substring(2));
        int vy = Integer.parseInt(velocities[1]);

        return new Robot(x, y, vx, vy);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(int mapWidth, int mapHeight, int remainingMoves) {
        x = (x + vx + mapWidth) % mapWidth;
        y = (y + vy + mapHeight) % mapHeight;

        if (remainingMoves > 1) move(mapWidth, mapHeight, remainingMoves-1);
    }

    public int getQuarter(int mapWidth, int mapHeight) {
        double middleX = Math.floor((double) mapWidth / 2);
        double middleY = Math.floor((double) mapHeight / 2);

        if (x < middleX) {
            if (y < middleY) {
                return 1;
            } else if (y > middleY) {
                return 3;
            }
        } else if (x > middleX) {
            if (y < middleY) {
                return 2;
            } else if (y > middleY) {
                return 4;
            }
        }

        return -1;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
