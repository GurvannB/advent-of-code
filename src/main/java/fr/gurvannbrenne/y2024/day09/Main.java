package fr.gurvannbrenne.y2024.day09;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static void main(String[] args) {
        Timestamp start = Timestamp.valueOf(LocalDateTime.now());

        List<String> lines = readFile("src/main/resources/y2024/day09/input.txt");
        Record rec = new Record(lines.get(0));
        System.out.println("Part 1 : "+rec.getChecksum());
        System.out.println("Part 2 : "+rec.getChecksumPart2());

        Timestamp end = Timestamp.valueOf(LocalDateTime.now());
        System.out.println("Execution time: "+((double) (end.getTime()-start.getTime())/1000)+"s");
    }
}
