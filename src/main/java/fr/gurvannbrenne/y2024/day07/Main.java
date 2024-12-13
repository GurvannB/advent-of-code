package fr.gurvannbrenne.y2024.day07;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static void main(String[] args) {
        Long totalPartOne = readFile("src/main/resources/y2024/day07/input.txt", (line) -> Record.parseLine(line).verify(false))
                .stream().filter(Record::isValid)
                .mapToLong(Record::getTotal)
                .sum();

        Long totalPartTwo = readFile("src/main/resources/y2024/day07/input.txt", (line) -> Record.parseLine(line).verify(true))
                .stream().filter(Record::isValid)
                .mapToLong(Record::getTotal)
                .sum();

        System.out.println("Part 1 : "+totalPartOne);
        System.out.println("Part 2 : "+totalPartTwo);
    }
}
