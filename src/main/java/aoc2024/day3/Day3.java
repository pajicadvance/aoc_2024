package aoc2024.day3;

import aoc2024.Util;
import it.unimi.dsi.fastutil.chars.CharArrayList;

public class Day3 {

    public static void run() {
        System.out.println("----- Day 3 -----");

        CharArrayList chars = Util.getInputChars("day3");

        StringBuilder str = new StringBuilder();
        boolean mulEnabled = true;
        int num1 = -1;
        int result = 0;
        int resultConditional = 0;
        for (int i = 0; i < chars.size() - 6; i++) {
            if (str.isEmpty()) {
                for (int j = 0; j < 7; j++) str.append(chars.getChar(i + j));
                if (str.toString().startsWith("do()")) mulEnabled = true;
                if (str.toString().startsWith("don't()")) mulEnabled = false;
                if (str.toString().startsWith("mul(")) {
                    str.setLength(0);
                    str.append("mul(");
                    i = i + 4;
                }
                else str.setLength(0);
            }
            if (str.toString().startsWith("mul(") && str.toString().endsWith(",")) {
                String numStr = str.substring(str.indexOf("(") + 1, str.length() - 1);
                num1 = Integer.parseInt(numStr);
            }
            if (str.toString().startsWith("mul(")) {
                char c = chars.getChar(i);
                if (Character.isDigit(c)) str.append(c);
                else if (c == ',' && num1 == -1) str.append(",");
                else if (c == ')' && num1 != -1) {
                    String numStr = str.substring(str.indexOf(",") + 1, str.length());
                    int num2 = Integer.parseInt(numStr);
                    result += num1 * num2;
                    if (mulEnabled) resultConditional += num1 * num2;
                    num1 = -1;
                    str.setLength(0);
                }
                else {
                    num1 = -1;
                    str.setLength(0);
                }
            }
        }

        System.out.println("Result: " + result);
        System.out.println("Result with condition: " + resultConditional);
    }
}
