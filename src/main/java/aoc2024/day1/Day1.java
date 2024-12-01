package aoc2024.day1;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import aoc2024.Util;
import java.util.Collections;

public class Day1 {

    public static void run() {
        IntArrayList list1 = new IntArrayList();
        IntArrayList list2 = new IntArrayList();

        Util.getInput("/inputs/day1").forEach(line -> {
            String[] split = line.split("\\s+");
            list1.add(Integer.parseInt(split[0]));
            list2.add(Integer.parseInt(split[1]));
        });

        Collections.sort(list1);
        Collections.sort(list2);

        int totalDistance = 0;
        int similarityScore = 0;

        for (int i = 0; i < list1.size(); i++) {
            totalDistance += Math.abs(list1.getInt(i) - list2.getInt(i));
            int occurrences = 0;
            for (int j = 0; j < list2.size(); j++) {
                if (list2.getInt(j) == list1.getInt(i)) occurrences++;
            }
            similarityScore += list1.getInt(i) * occurrences;
        }

        System.out.println("----- Day 1 -----" + "\nTotal distance: " + totalDistance + "\nSimilarity score: " + similarityScore);
    }
}
