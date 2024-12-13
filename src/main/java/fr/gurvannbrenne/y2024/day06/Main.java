package fr.gurvannbrenne.y2024.day06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.gurvannbrenne.Common.readFile;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>(readFile("src/main/resources/y2024/day06/input.txt"));
        ArrayList<ArrayList<Slot>> map = new ArrayList<>();

        int guardPosX = -1;
        int guardPosY = -1;

        for (int y = 0; y < lines.size(); y++) {
            char[] chars = lines.get(y).toCharArray();
            ArrayList<Slot> slots = new ArrayList<>();
            for (int x = 0; x < chars.length; x++) {
                if (chars[x] == '^') {
                    guardPosX = x;
                    guardPosY = y;
                }
                slots.add(new Slot(chars[x] == '#', x, y));
            }
            map.add(slots);
        }

        if (guardPosX == -1 || guardPosY == -1) {
            System.out.println("No guard detected");
            return;
        }

        Map<Slot, List<Direction>> visited = new HashMap<>();
        isInfinite(map, guardPosX, guardPosY, Direction.UP, visited);
        System.out.println("Part 1 : " + visited.size());

        int totalInfinite = 0;
        for (Slot slot : visited.keySet()) {
            slot.setIsWall(true);
            if (isInfinite(map, guardPosX, guardPosY, Direction.UP, new HashMap<>())) {
                totalInfinite++;
            }
            slot.setIsWall(false);
        }
        System.out.println("Part 2 : " + totalInfinite);
    }

    public static boolean isInfinite(ArrayList<ArrayList<Slot>> map, int baseX, int baseY, Direction baseDirection, Map<Slot, List<Direction>> visited) {
        int x = baseX;
        int y = baseY;
        Direction direction = baseDirection;

        while (isPosValid(map, x, y)) {
            Slot currentSlot = map.get(y).get(x);

            if (visited.get(currentSlot) == null) {
                visited.put(currentSlot, new ArrayList<>(List.of(direction)));
            } else {
                List<Direction> directions = visited.get(currentSlot);
                if (directions.contains(direction)) {
                    return true;
                } else {
                    visited.get(currentSlot).add(direction);
                }
            }

            if (isPosValid(map, x + direction.vectorX, y + direction.vectorY) && map.get(y + direction.vectorY).get(x + direction.vectorX).getIsWall()) {
                direction = direction.nextIfCollide;
            } else {
                x += direction.vectorX;
                y += direction.vectorY;
            }
        }

        return false;
    }

    public static boolean isPosValid(ArrayList<ArrayList<Slot>> map, Integer x, Integer y) {
        return x >= 0 && y >= 0 && x < map.get(0).size() && y < map.size();
    }
}
