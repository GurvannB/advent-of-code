package fr.gurvannbrenne.y2024.day12;

import java.util.ArrayList;
import java.util.List;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static void main(String[] args) {
        List<List<Character>> map = readFile("src/main/resources/y2024/day12/input.txt", (l) -> new ArrayList<>(l.chars().mapToObj(c -> (char) c).toList()));

        List<Plot> plots = new ArrayList<>();
        Long totalPartOne = 0L;
        Long totalPartTwo = 0L;
        for (int y = 0; y < map.size(); y++) {
            for (int x = 0; x < map.get(y).size(); x++) {
                if (map.get(y).get(x) != null) {
                    Plot plt = new Plot(map.get(y).get(x));
                    plots.add(plt);
                    explore(map, plt, x, y);
                    totalPartOne += (long) plt.getArea() * plt.getPerimeter();
                    totalPartTwo += (long) plt.getArea() * plt.getNbSides();
                }
            }
        }
        System.out.println("Part 1 : " + totalPartOne);
        System.out.println("Part 2 : " + totalPartTwo);
    }

    public static boolean explore(List<List<Character>> map, Plot plot, int currentX, int currentY) {
        if (!isInBound(map, currentX, currentY)) return true;
        Character currentPlant = map.get(currentY).get(currentX);
        if (currentPlant == plot.getPlant() && currentPlant != null) {
            plot.addPosition(new Position(currentX, currentY));
            map.get(currentY).set(currentX, null);

            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    if (Math.abs(dx - dy) == 1) {
                        if (explore(map, plot, currentX + dx, currentY + dy))
                            plot.incrementPerimeter();
                    }
                }
            }
            return false;
        }
        return !plot.getPositions().contains(new Position(currentX, currentY));
    }

    public static boolean isInBound(List<List<Character>> map, int x, int y) {
        return y >= 0 && y < map.size() && x >= 0 && x < map.get(y).size();
    }
}
