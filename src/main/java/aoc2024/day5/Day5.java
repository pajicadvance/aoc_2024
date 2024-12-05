package aoc2024.day5;

import aoc2024.Util;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;

import java.util.ArrayList;

public class Day5 {

    public static void run() {
        System.out.println("----- Day 5 -----");

        ArrayList<IntIntImmutablePair> rules = new ArrayList<>();
        ArrayList<IntArrayList> updates = new ArrayList<>();

        Util.getInputLines("day5").forEach(line -> {
            if (line.contains("|")) {
                String[] split = line.split("\\|");
                rules.add(new IntIntImmutablePair(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
            }
            else if (line.contains(",")) {
                String[] split = line.split(",");
                IntArrayList update = new IntArrayList();
                for (String s : split) update.add(Integer.parseInt(s));
                updates.add(update);
            }
        });

        int correctTotal = 0;
        int incorrectTotal = 0;
        for (IntArrayList update : updates) {
            ArrayList<IntIntImmutablePair> applicableRules = new ArrayList<>();
            for (IntIntImmutablePair rule : rules)
                if (update.contains(rule.leftInt()) && update.contains(rule.rightInt()))
                    applicableRules.add(rule);
            boolean isCorrect = applyRules(update, applicableRules);
            if (isCorrect) correctTotal += update.getInt(update.size() / 2);
            else incorrectTotal += update.getInt(update.size() / 2);
        }

        System.out.println("Correct total: " + correctTotal);
        System.out.println("Incorrect total: " + incorrectTotal);
    }

    private static boolean applyRules(IntArrayList update, ArrayList<IntIntImmutablePair> applicableRules) {
        boolean isCorrect = true;
        for (IntIntImmutablePair rule : applicableRules) {
            int leftIndex = update.indexOf(rule.leftInt());
            int rightIndex = update.indexOf(rule.rightInt());
            if (leftIndex != -1 && rightIndex != -1 && leftIndex > rightIndex) {
                isCorrect = false;
                int helper = update.getInt(leftIndex);
                update.set(leftIndex, update.getInt(rightIndex));
                update.set(rightIndex, helper);
                applyRules(update, applicableRules);
            }
        }
        return isCorrect;
    }
}
