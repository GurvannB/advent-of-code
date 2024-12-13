package fr.gurvannbrenne.y2024.day07;

import java.util.Arrays;
import java.util.List;

public class Record {
    private Long total;
    private List<Long> numbers;
    private Boolean valid = null;

    private Record(Long total, List<Long> numbers) {
        this.total = total;
        this.numbers = numbers;
    }

    static Record parseLine(String line) {
        String[] parts = line.split(": ");
        Long total = Long.parseLong(parts[0]);

        List<Long> numbers = Arrays.stream(parts[1].split(" ")).map(Long::parseLong).toList();

        return new Record(total, numbers);
    }

    private boolean isRecursiveValidPartOne(Long acc, List<Long> numbers) {
        if (numbers.size() == 1) {
            return acc + numbers.get(0) == total || acc * numbers.get(0) == total;
        } else {
            return isRecursiveValidPartOne(acc + numbers.get(0), numbers.subList(1, numbers.size())) || isRecursiveValidPartOne(acc * numbers.get(0), numbers.subList(1, numbers.size()));
        }
    }

    private boolean isRecursiveValidPartTwo(Long acc, List<Long> numbers) {
        if (numbers.size() == 1) {
            return acc + numbers.get(0) == total || acc * numbers.get(0) == total || Long.parseLong(acc+""+numbers.get(0)) == total;
        } else {
            return isRecursiveValidPartTwo(acc + numbers.get(0), numbers.subList(1, numbers.size())) ||
                    isRecursiveValidPartTwo(acc * numbers.get(0), numbers.subList(1, numbers.size())) ||
                    isRecursiveValidPartTwo(Long.parseLong(acc+""+numbers.get(0)), numbers.subList(1, numbers.size()));
        }
    }

    public Record verify(boolean partTwo) {
        if (numbers.size() == 1) {
            valid = numbers.get(0).equals(total);
        } else {
            valid = partTwo ? isRecursiveValidPartTwo(0L, numbers) : isRecursiveValidPartOne(0L, numbers);
        }
        return this;
    }

    public boolean isValid() {
        return valid != null && valid;
    }

    public Long getTotal() {
        return total;
    }

    public String toString() {
        return total+": "+numbers+" ("+valid+")";
    }
}
