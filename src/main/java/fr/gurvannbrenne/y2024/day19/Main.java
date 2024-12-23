package fr.gurvannbrenne.y2024.day19;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static void main(String[] args) {
        List<String> lines = readFile("src/main/resources/y2024/day19/input.txt");

        List<String> patterns = List.of(lines.get(0).split(", "));
        List<String> designs = lines.subList(2, lines.size());

        long totalPartOne = 0;
        long totalPartTwo = 0;
        for (String design : designs) {
            long possibleArrangements = possibleArrangements(design, patterns, new HashMap<>());
            if (possibleArrangements > 0) {
                totalPartOne++;
                totalPartTwo += possibleArrangements;
            }
        }
        System.out.println("Part 1: " + totalPartOne);
        System.out.println("Part 2: " + totalPartTwo);
    }

    public static long possibleArrangements(String toReproduce, List<String> patterns, Map<String, Long> alreadyCalculated) {
        if (alreadyCalculated.containsKey(toReproduce)) return alreadyCalculated.get(toReproduce);
        if (toReproduce.isEmpty()) return 1;
        long possibleArrangements = 0;
        for (String pattern: patterns) {
            if (toReproduce.startsWith(pattern)) {
                possibleArrangements += possibleArrangements(toReproduce.substring(pattern.length()), patterns, alreadyCalculated);
            }
        }
        alreadyCalculated.put(toReproduce, possibleArrangements);
        if (possibleArrangements < 0) {
            System.out.println();
        }
        return possibleArrangements;
    }
}
