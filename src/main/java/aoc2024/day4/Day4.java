package aoc2024.day4;

import aoc2024.Util;

import java.util.ArrayList;

public class Day4 {

    private static final StringBuilder str = new StringBuilder();

    public static void run() {
        System.out.println("----- Day 4 -----");

        ArrayList<char[]> matrix = new ArrayList<>();
        Util.getInputLines("day4").forEach(line -> matrix.add(line.toCharArray()));
        char[][] m = new char[matrix.size()][];
        for (int i = 0; i < matrix.size(); i++) m[i] = matrix.get(i);

        int xmasAppearances = 0;
        int mas2Appearances = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 'X') {
                    if (xmasSearch(m, i, j, Direction.N)) xmasAppearances++;
                    if (xmasSearch(m, i, j, Direction.S)) xmasAppearances++;
                    if (xmasSearch(m, i, j, Direction.E)) xmasAppearances++;
                    if (xmasSearch(m, i, j, Direction.W)) xmasAppearances++;
                    if (xmasSearch(m, i, j, Direction.NE)) xmasAppearances++;
                    if (xmasSearch(m, i, j, Direction.SW)) xmasAppearances++;
                    if (xmasSearch(m, i, j, Direction.NW)) xmasAppearances++;
                    if (xmasSearch(m, i, j, Direction.SE)) xmasAppearances++;
                }
                if (m[i][j] == 'A' && mas2Search(m, i, j)) mas2Appearances++;
            }
        }

        System.out.println("XMAS appearances: " + xmasAppearances);
        System.out.println("X-MAS appearances: " + mas2Appearances);
    }

    private static boolean xmasSearch(char[][] m, int i, int j, Direction dir) {
        if (boundCheck(m, i, j, dir)) {
            str.append('X').append(m[i + dir.x][j + dir.y]).append(m[i + 2*dir.x][j + 2*dir.y]).append(m[i + 3*dir.x][j + 3*dir.y]);
            if (str.toString().equals("XMAS")) {
                str.setLength(0);
                return true;
            }
            str.setLength(0);
        }
        return false;
    }

    private static boolean mas2Search(char[][] m, int i, int j) {
        if (i > 0 && i < m.length - 1 && j > 0 && j < m[i].length - 1) {
            str.append(m[i - 1][j - 1]).append('A').append(m[i + 1][j + 1]);
            if (str.toString().equals("MAS") || str.toString().equals("SAM")) {
                str.setLength(0);
                str.append(m[i - 1][j + 1]).append('A').append(m[i + 1][j - 1]);
                if (str.toString().equals("MAS") || str.toString().equals("SAM")) {
                    str.setLength(0);
                    return true;
                }
            }
            str.setLength(0);
        }
        return false;
    }

    private static boolean boundCheck(char[][] m, int i, int j, Direction dir) {
        switch (dir) {
            case N -> {
                return i + 3*dir.x >= 0;
            }
            case E -> {
                return j + 3*dir.y < m[i].length;
            }
            case S -> {
                return i + 3*dir.x < m.length;
            }
            case W -> {
                return j + 3*dir.y >= 0;
            }
            case NE -> {
                return i + 3*dir.x >= 0 && j + 3*dir.y < m[i].length;
            }
            case SE -> {
                return i + 3*dir.x < m.length && j + 3*dir.y < m[i].length;
            }
            case SW -> {
                return i + 3*dir.x < m.length && j + 3*dir.y >= 0;
            }
            case NW -> {
                return i + 3*dir.x >= 0 && j + 3*dir.y >= 0;
            }
            default -> {
                return false;
            }
        }
    }

    enum Direction {
        N(-1, 0),
        NE(-1, 1),
        E(0, 1),
        SE(1, 1),
        S(1, 0),
        SW(1, -1),
        W(0, -1),
        NW(-1, -1),;

        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
