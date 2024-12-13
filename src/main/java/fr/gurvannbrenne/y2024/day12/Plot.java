package fr.gurvannbrenne.y2024.day12;

import java.util.HashSet;
import java.util.Set;

public class Plot {
    private Character plant;
    private Set<Position> positions;
    private int perimeter;

    public Plot(Character plant) {
        this.plant = plant;
        this.positions = new HashSet<>();
        this.perimeter = 0;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void addPosition(Position pos) {
        this.positions.add(pos);
    }

    public Character getPlant() {
        return plant;
    }

    public int getArea() {
        return positions.size();
    }

    public int getPerimeter() {
        return perimeter;
    }

    public void incrementPerimeter() {
        perimeter++;
    }

    public String toString() {
        return "Reg: " + plant + " " + getArea() + " * " + getPerimeter() + " = " + (getArea() * getPerimeter()) + "(" + getNbSides() + " sides)";
    }

    public int getNbSides() {
        int minX = -1;
        int maxX = -1;
        int minY = -1;
        int maxY = -1;
        for (Position pos : positions) {
            if (minX == -1) minX = pos.x();
            if (maxX == -1) maxX = pos.x();
            if (minY == -1) minY = pos.y();
            if (maxY == -1) maxY = pos.y();

            minX = Math.min(minX, pos.x());
            maxX = Math.max(maxX, pos.x());
            minY = Math.min(minY, pos.y());
            maxY = Math.max(maxY, pos.y());
        }
        int width = maxX - minX + 3;
        int height = maxY - minY + 3;
        Boolean[][] map = new Boolean[height + 2][width + 2];
        for (int y = 0; y < height + 2; y++) {
            for (int x = 0; x < width + 2; x++) {
                map[y][x] = positions.contains(new Position(minX + x - 1, minY + y - 1));
            }
        }

//        int totalSides = 0;
//        for (int y = 0; y < map.length; y++) {
//            for (int x = 0; x < map[y].length; x++) {
//                int diffNeighbors = nbDifferentNeighbors(map, x, y);
//                if (diffNeighbors > 1) {
//                    totalSides += diffNeighbors - 1;
//                }
//            }
//        }

        // rows
        int totalSides = 0;

        /*
        - - - - - - -
        - R R R R - -
        - R R R R - -
        - - - R R R -
        - - - R - - -
        - - - - - - -
         */

        for (int y=0; y<height; y++) {
            if (y < height-1)
                for (int x=0; x<width; x++) {
                    if (!map[y][x] && map[y+1][x]) {
                        while (!map[y][x] && map[y+1][x]) x++;
                        totalSides++;
                    }
                }
            if (y > 0)
                for (int x=0; x<width; x++) {
                    if (!map[y][x] && map[y-1][x]) {
                        while (!map[y][x] && map[y-1][x]) x++;
                        totalSides++;
                    }
                }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++)
                if (map[y][x + 1] && !map[y][x]) {
                while (map[y + 1][x + 1] && !map[y][x]) y++;
                totalSides++;
            }
            for (int y = 0; y < height; y++)
                if (map[y][x] && !map[y][x + 1]) {
                while (map[y + 1][x] && !map[y][x + 1]) y++;
                totalSides++;
            }
        }

        return totalSides;
    }
}
