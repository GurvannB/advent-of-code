package fr.gurvannbrenne.y2015.day02;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static void main(String[] args) {
        int totalWrappingPaperSurface = readFile("src/main/resources/y2015/day02/input.txt", (l) -> {
            Gift g = Gift.parseLine(l);
            return g.getNecessaryWrappingPaper();
        }).stream().mapToInt(Integer::intValue).sum();

        System.out.println("Part 1: "+totalWrappingPaperSurface);

        int totalRibbon = readFile("src/main/resources/y2015/day02/input.txt", (l) -> {
            Gift g = Gift.parseLine(l);
            return g.getNecessaryRibbon();
        }).stream().mapToInt(Integer::intValue).sum();
        System.out.println("Part 2: "+totalRibbon);
    }
}
