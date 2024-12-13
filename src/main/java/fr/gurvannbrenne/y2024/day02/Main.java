package fr.gurvannbrenne.y2024.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static final int MIN_GAP = 1;
    public static final int MAX_GAP = 3;

    public static void main(String[] args) {
        List<String> lines = readFile("src/main/resources/y2024/day02/input.txt");
        List<Report> reports = lines.stream().map(Main::lineToReport).toList();

        int validReportsPartOne = 0;
        int validReportsPartTwo = 0;
        for (Report report : reports) {
            if (isTheLineSafe(report.getNumbers())) {
                validReportsPartOne++;
                validReportsPartTwo++;
            } else if (isTheLineSafeWithOneError(report)) validReportsPartTwo++;
        }
        System.out.println("Part one : "+validReportsPartOne);
        System.out.println("Part two : "+validReportsPartTwo);
    }

    static boolean isTheLineSafeWithOneError(Report report) {
        if (isTheLineSafe(report.getNumbers())) return true;
        for (int i=0; i<report.getNumbers().size(); i++) {
            List<Integer> numbers = new ArrayList<>(report.getNumbers());
            numbers.remove(i);
            if (isTheLineSafe(numbers)) return true;
        }
        return false;
    }

    static boolean isTheLineSafe(List<Integer> numbers) {
        if (numbers.size() < 2) return true;
        int sign = numbers.get(1) - numbers.get(0);
        if (sign > 0) {
            // croissant
            for (int i=0; i<numbers.size()-1; i++) {
                int gap = numbers.get(i+1) - numbers.get(i);
                if (gap < MIN_GAP || gap > MAX_GAP) return false;
            }
        } else if (sign < 0) {
            // déscroissant
            for (int i=0; i<numbers.size()-1; i++) {
                int gap = numbers.get(i+1) - numbers.get(i);
                if (gap > MIN_GAP*-1 || gap < MAX_GAP*-1) return false;
            }
        } else return !(MIN_GAP > 0);
        return true;
    }

    static Report lineToReport(String line) {
        List<Integer> numbers = Arrays.stream(line.split(" "))
                .map(Integer::parseInt)
                .toList();
        return new Report(numbers);
    }
}
