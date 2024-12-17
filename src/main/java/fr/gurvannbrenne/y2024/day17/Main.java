package fr.gurvannbrenne.y2024.day17;

import java.util.List;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static void main(String[] args) {
        List<String> lines = readFile("src/main/resources/y2024/day17/sample2.txt");
        Computer computer = Computer.parse(lines.get(0), lines.get(1), lines.get(2), lines.get(4));

//        System.out.println("Part 1: "+computer.getProgramOutput().toString().replaceAll("[\\[\\] ]",""));
//        System.out.println("Part 2: " + computer.solvePartTwo());
    }
}
