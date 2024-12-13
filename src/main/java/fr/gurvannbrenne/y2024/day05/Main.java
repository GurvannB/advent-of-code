package fr.gurvannbrenne.y2024.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fr.gurvannbrenne.Common.readFile;

enum Mode {
    RULES, RECORDS;
}

public class Main {
    public static void main(String[] args) {
        List<String> file = readFile("src/main/resources/y2024/day05/input.txt");

        List<Rule> rules = new ArrayList<>();
        List<Record> records = new ArrayList<>();

        Mode mode = Mode.RULES;
        for (String line : file) {
            if (line.isEmpty()) {
                mode = Mode.RECORDS;
            } else if (mode == Mode.RULES) {
                rules.add(parseRule(line));
            } else if (mode == Mode.RECORDS) {
                records.add(parseRecord(line));
            }
        }

        int totalPartOne = 0;
        int totalPartTwo = 0;
        for (Record record : records) {
            List<Rule> constraints = rules.stream().filter((rule) -> rule.implyTwoNumbers(record.numbers())).toList();
            if (record.respectsConstraints(constraints)) {
                totalPartOne += record.numbers().get(record.numbers().size() / 2);
            } else {
                record.fixWithConstraints(constraints);
                totalPartTwo += record.numbers().get(record.numbers().size() / 2);
            }
        }
        System.out.println("Part 1 : " + totalPartOne);
        System.out.println("Part 2 : " + totalPartTwo);
    }

    public static Rule parseRule(String line) {
        String[] chunks = line.split("\\|");
        return new Rule(Integer.parseInt(chunks[0]), Integer.parseInt(chunks[1]));
    }

    public static Record parseRecord(String line) {
        List<Integer> numbers = new ArrayList<>(Arrays.stream(line.split(",")).map(Integer::parseInt).toList());
        return new Record(numbers);
    }
}
