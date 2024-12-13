package fr.gurvannbrenne.y2024.day10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static void main(String[] args) {
        List<List<Slot>> map = readFile("src/main/resources/y2024/day10/input.txt", (line, y) -> {
            List<Slot> slots = new ArrayList<>();
            for (int x = 0; x < line.toCharArray().length; x++) {
                int height = Character.getNumericValue(line.charAt(x));
                slots.add(new Slot(x, y, height));
            }
            return slots;
        });

        int totalPartOne = getTrailHeads(map).stream().mapToInt((slot) -> getScoreFromPosition(map, slot.getX(), slot.getY(), new ArrayList<>(), false)).sum();
        System.out.println("Part 1 : " + totalPartOne);

        int totalPartTwo = getTrailHeads(map).stream().mapToInt((slot) -> getScoreFromPosition(map, slot.getX(), slot.getY(), new ArrayList<>(), true)).sum();
        System.out.println("Part 2 : " + totalPartTwo);
    }

    public static List<Slot> getTrailHeads(List<List<Slot>> map) {
        return map.stream().flatMap(Collection::stream).filter(Slot::isTrailHead).toList();
    }

    public static int getScoreFromPosition(List<List<Slot>> map, int x, int y, List<Slot> visitedTrailHeads, boolean rating) {
        int currentHeight = map.get(y).get(x).getHeight();
        Slot current = map.get(y).get(x);
        int score = 0;
        if (currentHeight == 9) {
            if (visitedTrailHeads.contains(current)) {
                if (rating) score++;
            } else {
                score++;
                visitedTrailHeads.add(current);
            }
        }
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (Math.abs(Math.abs(dx) - Math.abs(dy)) == 1
                        && isInBound(map, x + dx, y + dy)
                        && map.get(y + dy).get(x + dx).getHeight() - currentHeight == 1) {
                    score += getScoreFromPosition(map, x + dx, y + dy, visitedTrailHeads, rating);
                }
            }
        }
        return score;
    }

    public static boolean isInBound(List<List<Slot>> map, int x, int y) {
        return y >= 0 && y < map.size() && x >= 0 && x < map.get(y).size();
    }
}
