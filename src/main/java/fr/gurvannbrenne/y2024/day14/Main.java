package fr.gurvannbrenne.y2024.day14;

import java.util.List;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static void main(String[] args) {
        partOne();
        partTwo();
        showTree();
    }

    public static void partOne() {
        List<Robot> robots = readFile("src/main/resources/y2024/day14/input.txt", Robot::parseLine);

        int mapWidth = 101;
        int mapHeight = 103;

        int[] totalQuarters = new int[]{0, 0, 0, 0};
        for (Robot robot : robots) {
            robot.move(mapWidth, mapHeight, 100);
            int quarter = robot.getQuarter(mapWidth, mapHeight);
            if (quarter != -1) totalQuarters[quarter - 1]++;
        }
        for (int i = 0; i < totalQuarters.length; i++) {
            if (totalQuarters[i] == 0) totalQuarters[i] = 1;
        }

        int totalPartOne = totalQuarters[0] * totalQuarters[1] * totalQuarters[2] * totalQuarters[3];
        System.out.println("Part 1: " + totalPartOne);
    }

    public static void partTwo() {
        List<Robot> robots = readFile("src/main/resources/y2024/day14/input.txt", Robot::parseLine);

        int mapWidth = 101;
        int mapHeight = 103;

        int i = 0;
        int posFound = -1;
        while (posFound == -1) {
            Boolean[][] map = occupiedMap(robots, mapWidth, mapHeight);
            for (int y = 0; y < mapHeight; y++) {
                for (int x = 0; x < mapWidth - 10; x += 10) {
                    if (map[y][x] != null
                            && map[y][x + 1] != null
                            && map[y][x + 2] != null
                            && map[y][x + 3] != null
                            && map[y][x + 4] != null
                            && map[y][x + 5] != null
                            && map[y][x + 6] != null
                            && map[y][x + 7] != null
                            && map[y][x + 8] != null
                            && map[y][x + 9] != null) {
                        posFound = i;
                        break;
                    }
                }
            }
            for (Robot r : robots) r.move(mapWidth, mapHeight, 1);
            i++;
        }

        System.out.println("Part 2: " + posFound);
    }

    public static Boolean[][] occupiedMap(List<Robot> robots, int mapWidth, int mapHeight) {
        Boolean[][] map = new Boolean[mapHeight][mapWidth];

        for (Robot robot : robots) {
            map[robot.y][robot.x] = true;
        }

        return map;
    }

    public static void showMap(Boolean[][] map) {
        for (Boolean[] row : map) {
            for (Boolean col : row) {
                System.out.print(col == null ? " " : "0");
            }
            System.out.println();
        }
    }

    public static void showTree() {
        List<Robot> robots = readFile("src/main/resources/y2024/day14/input.txt", Robot::parseLine);

        int mapWidth = 101;
        int mapHeight = 103;

        for (Robot robot : robots) {
            robot.move(mapWidth, mapHeight, 6888);
        }

        showMap(occupiedMap(robots, mapWidth, mapHeight));
    }
}
