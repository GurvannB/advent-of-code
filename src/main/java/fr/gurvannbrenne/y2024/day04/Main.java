package fr.gurvannbrenne.y2024.day04;

import java.util.List;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static String toFind = "XMAS";
    public static String toFindReversed = new StringBuilder(toFind).reverse().toString();

    public static List<String> toFind2 = List.of("MMASS", "SMASM", "SSAMM", "MSAMS");

    public static void main(String[] args) {
        List<String> lines = readFile("src/main/resources/y2024/day04/input.txt");

        int total = 0;
        int total2 = 0;
        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                if (col + toFind.length() <= lines.get(row).length()) {
                    String possibility = lines.get(row).substring(col, col + toFind.length());
                    if (possibility.equals(toFind) || possibility.equals(toFindReversed)) {
                        total += 1;
                    }
                }

                if (row + toFind.length() <= lines.size()) {
                    StringBuilder possibility = new StringBuilder();
                    for (int y = 0; y < toFind.length(); y++) {
                        possibility.append(lines.get(row + y).charAt(col));
                    }
                    if (possibility.toString().equals(toFind) || possibility.toString().equals(toFindReversed)) {
                        total += 1;
                    }
                }

                if (col + toFind.length() <= lines.get(row).length() && row + toFind.length() <= lines.size()) {
                    StringBuilder possibility = new StringBuilder();
                    int y = 0;
                    int x = 0;
                    while (y < toFind.length() || x < toFind.length()) {
                        possibility.append(lines.get(row + y).charAt(col + x));
                        y++;
                        x++;
                    }
                    if (possibility.toString().equals(toFind) || possibility.toString().equals(toFindReversed)) {
                        total += 1;
                    }
                }

                if (col - toFind.length() + 1 >= 0 && row + toFind.length() <= lines.size()) {
                    StringBuilder possibility = new StringBuilder();
                    int y = 0;
                    int x = 0;
                    while (y < toFind.length() || x < toFind.length()) {
                        possibility.append(lines.get(row + y).charAt(col - x));
                        y++;
                        x++;
                    }
                    if (possibility.toString().equals(toFind) || possibility.toString().equals(toFindReversed)) {
                        total += 1;
                    }
                }

                if (col + 2 < lines.get(row).length() && row + 2 < lines.size()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(lines.get(row).charAt(col));
                    sb.append(lines.get(row).charAt(col + 2));
                    sb.append(lines.get(row + 1).charAt(col + 1));
                    sb.append(lines.get(row + 2).charAt(col));
                    sb.append(lines.get(row + 2).charAt(col + 2));
                    String possibility = sb.toString();
                    if (toFind2.contains(possibility)) total2++;
                }
            }
        }

        System.out.println("Part 1 : "+total);
        System.out.println("Part 2 : "+total2);
    }
}
