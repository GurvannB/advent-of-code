package fr.gurvannbrenne.y2024.day13;

import java.util.ArrayList;
import java.util.List;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static void main(String[] args) {
        List<String> lines = readFile("src/main/resources/y2024/day13/input.txt");
        List<ClawMachineRecord> records = new ArrayList<>();
        long totalTokenPartOne = 0;
        long totalTokenPartTwo = 0;
        for (int i=0; i<lines.size(); i+=4) {
            records.add(ClawMachineRecord.parse(
                    lines.get(i),
                    lines.get(i+1),
                    lines.get(i+2)
            ));
            long totalTokenForClawPartOne = records.get(records.size()-1).getTotalToken();
            if (totalTokenForClawPartOne != -1) totalTokenPartOne += totalTokenForClawPartOne;
            long totalTokenForClawPartTwo = records.get(records.size()-1).getTotalTokenPart2();
            if (totalTokenForClawPartTwo != -1) totalTokenPartTwo += totalTokenForClawPartTwo;
        }
        System.out.println("Part 1: " + totalTokenPartOne);
        System.out.println("Part 2: " + totalTokenPartTwo);
    }
}
