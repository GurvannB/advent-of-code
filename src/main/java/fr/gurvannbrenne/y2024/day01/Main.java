package fr.gurvannbrenne.y2024.day01;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static List<Couple> fileToCouple(List<String> file) {
        List<Couple> couples = new ArrayList<>();
        for (String line : file) {
            String[] chunks = line.split("   ");
            couples.add(new Couple(Integer.parseInt(chunks[0]), Integer.parseInt(chunks[1])));
        }
        return couples;
    }

    public static void main(String[] args) {
        List<Couple> list = fileToCouple(readFile("src/main/resources/y2024/day01/input.txt"));
        List<Integer> listA = list.stream().map(Couple::getA).sorted().toList();
        List<Integer> listB = list.stream().map(Couple::getB).sorted().toList();

        System.out.println("Part one: "+partOne(listA, listB));
        System.out.println("Part two: "+partTwo(listA, listB));
    }

    public static int partOne(List<Integer> listA, List<Integer> listB) {
        Integer total = 0;
        for (int i = 0; i < listA.size(); i++) {
            int diff = Math.abs(listA.get(i) - listB.get(i));
            total += diff;
        }
        return total;
    }

    public static int partTwo(List<Integer> listA, List<Integer> listB) {
        Integer total = 0;
        for (int i = 0; i < listA.size(); i++) {
            Integer elm = listA.get(i);
            int occurrences = listB.stream().filter((number) -> Objects.equals(number, elm)).toList().size();
            total += occurrences * elm;
        }
        return total;
    }
}
