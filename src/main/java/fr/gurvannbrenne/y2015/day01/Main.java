package fr.gurvannbrenne.y2015.day01;

import java.util.List;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static void main(String[] args) {
        List<String> lines = readFile("src/main/resources/y2015/day01/input.txt");
        String line = lines.get(0);
        int floor = 0;
        Integer enterBasementAtPosition = null;
        char[] chars = line.toCharArray();
        for (int i=0; i<chars.length; i++) {
            char c = chars[i];
            if (c == '(')
                floor++;
            else
                floor--;

            if (floor == -1 && enterBasementAtPosition == null) enterBasementAtPosition = i+1;
        }

        System.out.println("Part 1: " + floor);
        System.out.println("Part 2: " + enterBasementAtPosition);
    }
}
