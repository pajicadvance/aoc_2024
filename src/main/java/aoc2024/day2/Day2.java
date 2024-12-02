package aoc2024.day2;

import aoc2024.Util;
import it.unimi.dsi.fastutil.ints.IntArrayList;

import java.util.ArrayList;

public class Day2 {

    public static void run() {
        System.out.println("----- Day 2 -----");

        ArrayList<IntArrayList> reports = new ArrayList<>();

        Util.getInput("day2").forEach(line -> {
            IntArrayList report = new IntArrayList();
            String[] levels = line.split("\\s+");
            for (String level : levels) report.add(Integer.parseInt(level));
            reports.add(report);
        });

        int safeReports = 0;
        int safeReportsWithDampening = 0;

        for (IntArrayList report : reports) {
            if (isSafeReport(report)) {
                safeReports++;
                safeReportsWithDampening++;
            }
            else {
                for (int i = 0; i < report.size(); i++) {
                    int removedIndex = report.removeInt(i);
                    if (isSafeReport(report)) {
                        safeReportsWithDampening++;
                        break;
                    }
                    report.add(i, removedIndex);
                }
            }
        }

        System.out.println("Safe reports : " + safeReports);
        System.out.println("Safe reports (with dampening): " + safeReportsWithDampening);
    }

    private static boolean isSafeReport(IntArrayList report) {
        SortState sortState = determineSortState(report);
        if (sortState == SortState.UNSORTED) return false;
        else {
            for (int i = 1; i < report.size() - 1; i++) {
                if (!isLevelAdjacentsSafe(report, i, sortState)) return false;
            }
        }
        return true;
    }

    private static SortState determineSortState(IntArrayList report) {
        SortState sortState = SortState.UNSORTED;
        for (int i = 1; i < report.size(); i++) {
            int level = report.getInt(i);
            int prevLevel = report.getInt(i - 1);
            if (prevLevel > level && sortState == SortState.ASCENDING) return SortState.UNSORTED;
            else if (prevLevel < level && sortState == SortState.DESCENDING) return SortState.UNSORTED;
            else {
                if (prevLevel > level) sortState = SortState.DESCENDING;
                if (prevLevel < level) sortState = SortState.ASCENDING;
            }
        }
        return sortState;
    }

    private static boolean isLevelAdjacentsSafe(IntArrayList report, int levelIndex, SortState sortState) {
        int level = report.getInt(levelIndex);
        int prevLevel = report.getInt(levelIndex - 1);
        int nextLevel = report.getInt(levelIndex + 1);
        int prevDiff = Math.abs(level - prevLevel);
        int nextDiff = Math.abs(level - nextLevel);
        if (prevDiff >= 1 && prevDiff <= 3 && nextDiff >= 1 && nextDiff <= 3) {
            if (sortState == SortState.ASCENDING && prevLevel < level && level < nextLevel) return true;
            if (sortState == SortState.DESCENDING && prevLevel > level && level > nextLevel) return true;
        }
        return false;
    }

    enum SortState {
        UNSORTED, ASCENDING, DESCENDING
    }
}
