package fr.gurvannbrenne.y2024.day15;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static fr.gurvannbrenne.Common.readFile;


public class Main {
    private static final Character ROBOT = '@';
    private static final Character WALL = '#';
    private static final Character BOX = 'O';
    private static final Character BOX_LEFT = '[';
    private static final Character BOX_RIGHT = ']';
    private static final Character EMPTY_SPACE = '.';
    private static final Map<Character, Vector> DIRECTIONS_CONFIG = Map.of(
            '>', new Vector(1, 0),
            'v', new Vector(0, 1),
            '<', new Vector(-1, 0),
            '^', new Vector(0, -1)
    );

    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partOne() {
        List<String> lines = readFile("src/main/resources/y2024/day15/input.txt");

        List<List<Character>> map = new ArrayList<>();
        List<Character> directions = new ArrayList<>();
        boolean directionsMode = false;

        int robotX = -1;
        int robotY = -1;

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            if (line.isEmpty()) directionsMode = true;
            else {
                if (directionsMode) {
                    for (char c : line.toCharArray()) directions.add(c);
                } else {
                    List<Character> mapLine = new ArrayList<>();
                    char[] chars = line.toCharArray();
                    for (int x = 0; x < chars.length; x++) {
                        char c = chars[x];
                        if (c == ROBOT) {
                            robotX = x;
                            robotY = y;
                        }
                        mapLine.add(c);
                    }
                    map.add(mapLine);
                }
            }
        }

        if (robotX == -1) {
            System.out.println("Robot not found");
            return;
        }

        for (Character direction : directions) {
            Vector vector = DIRECTIONS_CONFIG.get(direction);

            int vx = vector.vx();
            int vy = vector.vy();
            while (map.get(robotY + vy).get(robotX + vx) != WALL && map.get(robotY + vy).get(robotX + vx) != EMPTY_SPACE) {
                vx += vector.vx();
                vy += vector.vy();
            }
            if (map.get(robotY + vy).get(robotX + vx) == EMPTY_SPACE) {
                if (vx != vector.vx() || vy != vector.vy()) {
                    map.get(robotY + vy).set(robotX + vx, BOX);
                    map.get(robotY + vector.vy()).set(robotX + vector.vx(), EMPTY_SPACE);
                }
                map.get(robotY).set(robotX, EMPTY_SPACE);
                robotX = robotX + vector.vx();
                robotY = robotY + vector.vy();
                map.get(robotY).set(robotX, ROBOT);
            }
        }

        long total = 0L;
        for (int y = 0; y < map.size(); y++) {
            List<Character> line = map.get(y);
            for (int x = 0; x < line.size(); x++) {
                Character c = line.get(x);
                if (c == BOX) {
                    total += 100L * y + x;
                }
            }
        }

        System.out.println("Part 1: " + total);
    }

    public static void partTwo() {
        List<String> lines = readFile("src/main/resources/y2024/day15/input.txt");

        List<List<Character>> map = new ArrayList<>();
        List<Character> directions = new ArrayList<>();
        boolean directionsMode = false;

        for (String line : lines) {
            if (line.isEmpty()) directionsMode = true;
            else {
                if (directionsMode) {
                    for (char c : line.toCharArray()) directions.add(c);
                } else {
                    List<Character> mapLine = new ArrayList<>();
                    for (char c : line.toCharArray()) {
                        if (c == BOX) {
                            mapLine.add(BOX_LEFT);
                            mapLine.add(BOX_RIGHT);
                        } else {
                            mapLine.add(c);
                            if (c != ROBOT) mapLine.add(c);
                            else mapLine.add(EMPTY_SPACE);
                        }
                    }
                    map.add(mapLine);
                }
            }
        }

        int robotX = -1;
        int robotY = -1;

        for (int y = 0; y < map.size(); y++) {
            for (int x = 0; x < map.get(y).size(); x++) {
                char c = map.get(y).get(x);
                if (c == ROBOT) {
                    robotX = x;
                    robotY = y;
                }
            }
        }

        if (robotX == -1) {
            System.out.println("Robot not found");
            return;
        }

        for (Character direction : directions) {
            Vector vector = DIRECTIONS_CONFIG.get(direction);

            if (move(map, robotX + vector.vx(), robotY + vector.vy(), direction, true)) {
                move(map, robotX + vector.vx(), robotY + vector.vy(), direction);
                map.get(robotY).set(robotX, EMPTY_SPACE);
                robotX = robotX + vector.vx();
                robotY = robotY + vector.vy();
                map.get(robotY).set(robotX, ROBOT);
            }
        }

        long total = 0L;
        for (int y = 0; y < map.size(); y++) {
            List<Character> line = map.get(y);
            for (int x = 0; x < line.size(); x++) {
                Character c = line.get(x);
                if (c == BOX_LEFT) {
                    total += 100L * y + x;
                }
            }
        }

        System.out.println("Part 1: " + total);
    }

    public static boolean move(List<List<Character>> map, int x, int y, Character direction, boolean detectCollisions) {
        if (map.get(y).get(x) == WALL)
            return false;
        else if (map.get(y).get(x) == EMPTY_SPACE) {
            return true;
        } else {
            Vector vector = DIRECTIONS_CONFIG.get(direction);
            boolean canMove;
            if (direction == '<') {
                if (map.get(y).get(x) == BOX_RIGHT) {
                    return move(map, x - 1, y, direction, detectCollisions);
                } else {
                    canMove = move(map, x + vector.vx(), y, direction, detectCollisions);
                }
            } else if (direction == '>') {
                if (map.get(y).get(x) == BOX_LEFT) {
                    return move(map, x + 1, y, direction, detectCollisions);
                } else {
                    canMove = move(map, x + vector.vx(), y, direction, detectCollisions);
                }
            } else {
                char current = map.get(y).get(x);
                int xStep = current == BOX_LEFT ? 1 : -1;
                if (current == map.get(y + vector.vy()).get(x)) {
                    canMove = move(map, x, y + vector.vy(), direction, detectCollisions);
                } else {
                    canMove = move(map, x, y + vector.vy(), direction, detectCollisions)
                            && move(map, x + xStep, y + vector.vy(), direction, detectCollisions);
                }
            }
            if (canMove && !detectCollisions) {
                char current = map.get(y).get(x);

                switch (direction) {
                    case '<':
                        if (current == BOX_LEFT) {
                            map.get(y).set(x + vector.vx(), map.get(y).get(x));
                            map.get(y).set(x, EMPTY_SPACE);
                            map.get(y).set(x + 1 + vector.vx(), map.get(y).get(x + 1));
                            map.get(y).set(x + 1, EMPTY_SPACE);
                        } else {
                            map.get(y).set(x - 1 + vector.vx(), map.get(y).get(x - 1));
                            map.get(y).set(x - 1, EMPTY_SPACE);
                            map.get(y).set(x + vector.vx(), map.get(y).get(x));
                            map.get(y).set(x, EMPTY_SPACE);
                        }
                        break;
                    case '>':
                        if (current == BOX_LEFT) {
                            map.get(y).set(x + 1 + vector.vx(), map.get(y).get(x + 1));
                            map.get(y).set(x + 1, EMPTY_SPACE);
                            map.get(y).set(x + vector.vx(), map.get(y).get(x));
                            map.get(y).set(x, EMPTY_SPACE);
                        } else {
                            map.get(y).set(x + vector.vx(), map.get(y).get(x));
                            map.get(y).set(x, EMPTY_SPACE);
                            map.get(y).set(x - 1 + vector.vx(), map.get(y).get(x - 1));
                            map.get(y).set(x - 1, EMPTY_SPACE);
                        }
                        break;
                    default:
                        int xStep = current == BOX_LEFT ? 1 : -1;
                        map.get(y + vector.vy()).set(x, map.get(y).get(x));
                        map.get(y).set(x, EMPTY_SPACE);
                        map.get(y + vector.vy()).set(x + xStep, map.get(y).get(x + xStep));
                        map.get(y).set(x + xStep, EMPTY_SPACE);
                        break;
                }
            }
            return canMove;
        }
    }

    public static void move(List<List<Character>> map, int x, int y, Character direction) {
        move(map, x, y, direction, false);
    }

    public static void showMap(List<List<Character>> map) {
        for (List<Character> line : map) {
            for (Character c : line) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
