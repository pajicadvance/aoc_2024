package day1;

import it.unimi.dsi.fastutil.ints.IntArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class Day1 {

    public static void run() {
        IntArrayList list1 = new IntArrayList();
        IntArrayList list2 = new IntArrayList();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(Day1.class.getResource("/inputs/day1").toURI()))) {
            br.lines().forEach(line -> {
                String[] split = line.split("\\s+");
                list1.add(Integer.parseInt(split[0]));
                list2.add(Integer.parseInt(split[1]));
            });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace(System.err);
        }

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
