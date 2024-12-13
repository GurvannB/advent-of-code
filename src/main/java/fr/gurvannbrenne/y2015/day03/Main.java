package fr.gurvannbrenne.y2015.day03;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static void main(String[] args) {
        List<String> lines = readFile("src/main/resources/y2015/day03/input.txt");
        String line = lines.get(0);
        int x = 0;
        int y = 0;
        Set<String> houses = new HashSet<>();
        houses.add(x + "-" + y);
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            Direction direction = Direction.getPositionByDirection(c);
            x += direction.dx;
            y += direction.dy;
            houses.add(x + "-" + y);
        }
        System.out.println("Part 1: " + houses.size());

        int santaX = 0;
        int santaY = 0;
        int robSantaX = 0;
        int robSantaY = 0;
        Set<String> housesPartTwo = new HashSet<>();
        houses.add(santaX + "-" + santaY);
        for (int i = 0; i < line.length(); i+=2) {
            char c = line.charAt(i);
            Direction direction = Direction.getPositionByDirection(c);
            santaX += direction.dx;
            santaY += direction.dy;
            housesPartTwo.add(santaX + "-" + santaY);

            char robC = line.charAt(i+1);
            Direction robDirection = Direction.getPositionByDirection(robC);
            robSantaX += robDirection.dx;
            robSantaY += robDirection.dy;
            housesPartTwo.add(robSantaX + "-" + robSantaY);
        }
        System.out.println("Part 2: " + housesPartTwo.size());
    }
}
