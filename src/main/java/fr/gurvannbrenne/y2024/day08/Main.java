package fr.gurvannbrenne.y2024.day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static void main(String[] args) {
        Map<Character, List<Antenna>> antennas = new HashMap<>();
        List<String> lines = readFile("src/main/resources/y2024/day08/input.txt");

        int maxX = lines.get(0).length();
        int maxY = lines.size();

        for (int y=0; y<lines.size(); y++) {
            for (int x = 0; x < lines.get(y).length(); x++) {
                Character character = lines.get(y).charAt(x);
                if (character.toString().matches("[a-zA-Z0-9]")) {
                    Antenna slot = new Antenna(x, y);
                    if (antennas.containsKey(character)) {
                        antennas.get(character).add(slot);
                    } else {
                        List<Antenna> slots = new ArrayList<>();
                        slots.add(slot);
                        antennas.put(character, slots);
                    }
                }
            }
        }

        Set<String> positions1 = new HashSet<>();
        antennas.values().forEach((slots) -> {
            for (int i=0; i<slots.size(); i++) {
                for (int j=0; j<slots.size(); j++) {
                    if (i != j) {
                        Antenna antenna1 = slots.get(i);
                        Antenna antenna2 = slots.get(j);

                        int antinodeX = -1;
                        int antinodeY = -1;
                        if (antenna1.getX() > antenna2.getX()) {
                            antinodeX = antenna1.getX() + antenna1.getX() - antenna2.getX();
                        } else {
                            antinodeX = antenna1.getX() - (antenna2.getX() - antenna1.getX());
                        }
                        if (antenna1.getY() > antenna2.getY()) {
                            antinodeY = antenna1.getY() + antenna1.getY() - antenna2.getY();
                        } else {
                            antinodeY = antenna1.getY() - (antenna2.getY() - antenna1.getY());
                        }

                        if (isInBound(antinodeX, antinodeY, maxX, maxY)) {
                            positions1.add(antinodeX+"-"+antinodeY);
                        }
                    }
                }
            }
        });
        System.out.println("Part 1: "+positions1.size());

        Set<String> positions2 = new HashSet<>();
        antennas.values().forEach((slots) -> {
            for (int i=0; i<slots.size(); i++) {
                for (int j=0; j<slots.size(); j++) {
                    if (i != j) {
                        Antenna antenna1 = slots.get(i);
                        Antenna antenna2 = slots.get(j);
                        positions2.add(antenna1.getX()+"-"+antenna1.getY());
                        positions2.add(antenna2.getX()+"-"+antenna2.getY());

                        int dx = Math.abs(antenna2.getX() - antenna1.getX());
                        int dy = Math.abs(antenna2.getY() - antenna1.getY());

                        if (antenna1.getX() < antenna2.getX()) {
                            if (antenna1.getY() < antenna2.getY()) {
                                int antinodeX = antenna1.getX() - dx;
                                int antinodeY = antenna1.getY() - dy;

                                while (isInBound(antinodeX, antinodeY, maxX, maxY)) {
                                    positions2.add(antinodeX+"-"+antinodeY);
                                    antinodeX -= dx;
                                    antinodeY -= dy;
                                }
                            } else {
                                int antinodeX = antenna1.getX() - dx;
                                int antinodeY = antenna1.getY() + dy;

                                while (isInBound(antinodeX, antinodeY, maxX, maxY)) {
                                    positions2.add(antinodeX+"-"+antinodeY);
                                    antinodeX -= dx;
                                    antinodeY += dy;
                                }
                            }
                        } else {
                            if (antenna1.getY() < antenna2.getY()) {
                                int antinodeX = antenna1.getX() + dx;
                                int antinodeY = antenna1.getY() - dy;

                                while (isInBound(antinodeX, antinodeY, maxX, maxY)) {
                                    positions2.add(antinodeX+"-"+antinodeY);
                                    antinodeX += dx;
                                    antinodeY -= dy;
                                }
                            } else {
                                int antinodeX = antenna1.getX() + dx;
                                int antinodeY = antenna1.getY() + dy;

                                while (isInBound(antinodeX, antinodeY, maxX, maxY)) {
                                    positions2.add(antinodeX+"-"+antinodeY);
                                    antinodeX += dx;
                                    antinodeY += dy;
                                }
                            }
                        }

                        int antinodeX = -1;
                        int antinodeY = -1;
                        if (antenna1.getX() > antenna2.getX()) {
                            antinodeX = antenna1.getX() + antenna1.getX() - antenna2.getX();
                        } else {
                            antinodeX = antenna1.getX() - (antenna2.getX() - antenna1.getX());
                        }
                        if (antenna1.getY() > antenna2.getY()) {
                            antinodeY = antenna1.getY() + antenna1.getY() - antenna2.getY();
                        } else {
                            antinodeY = antenna1.getY() - (antenna2.getY() - antenna1.getY());
                        }

                        if (isInBound(antinodeX, antinodeY, maxX, maxY)) {
                            positions2.add(antinodeX+"-"+antinodeY);
                        }
                    }
                }
            }
        });
        System.out.println("Part 2: "+positions2.size());
    }

    public static boolean isInBound(int x, int y, int maxX, int maxY) {
        return x >= 0 && x < maxX && y >= 0 && y < maxY;
    }
}
