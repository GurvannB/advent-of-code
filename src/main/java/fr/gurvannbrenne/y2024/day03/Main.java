package fr.gurvannbrenne.y2024.day03;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static void main(String[] args) {
        List<String> lines = readFile("src/main/resources/y2024/day03/input.txt");
        String toDecode = String.join("", lines);

        Integer totalPartOne = getTotal(toDecode);
        System.out.println("Part 1 : "+totalPartOne);

        Integer totalPartTwo = getTotalWithDoDont(toDecode);
        System.out.println("Part 2 : "+totalPartTwo);
    }

    public static Integer getTotal(String line) {
        String regex = "(mul\\([0-9]+\\,[0-9]+\\))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        Integer total = 0;
        while (matcher.find()) {
            String operationString = matcher.group();
            String[] operands = operationString.substring(4, operationString.length() - 1).split(",");
            total += Arrays.stream(operands).mapToInt(Integer::parseInt).reduce(1, (a, b) -> a * b);
        }

        return total;
    }

    public static Integer getTotalWithDoDont(String line) {
        String regex = "(mul\\([0-9]{1,3}+\\,[0-9]{1,3}\\))|do\\(\\)|don't\\(\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        boolean shouldDo = true;
        Integer total = 0;
        while (matcher.find()) {
            String match = matcher.group();
            if (match.equals("do()")) {
                shouldDo = true;
            } else if (match.equals("don't()")) {
                shouldDo = false;
            } else if (shouldDo) {
                String operationString = matcher.group();
                String[] operands = operationString.substring(4, operationString.length() - 1).split(",");
                total += Arrays.stream(operands).mapToInt(Integer::parseInt).reduce(1, (a, b) -> a * b);
            }
        }

        return total;
    }
}
