package aoc2024;

import it.unimi.dsi.fastutil.chars.CharArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static List<String> getInputLines(String filename) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(Util.class.getResource("/inputs/" + filename).toURI()))) {
            return br.lines().collect(Collectors.toList());
        } catch (IOException | URISyntaxException | NullPointerException e) {
            System.out.println("Cannot read input, check file name!\n" + e.getMessage());
            return List.of();
        }
    }

    public static CharArrayList getInputChars(String filename) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(Util.class.getResource("/inputs/" + filename).toURI()))) {
            CharArrayList chars = new CharArrayList();
            int c;
            while ((c = br.read()) != -1) chars.add((char) c);
            return chars;
        } catch (IOException | URISyntaxException | NullPointerException e) {
            System.out.println("Cannot read input, check file name!\n" + e.getMessage());
            return CharArrayList.of();
        }
    }
}
