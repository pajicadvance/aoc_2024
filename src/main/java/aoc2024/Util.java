package aoc2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static List<String> getInput(String filename) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(Util.class.getResource("/inputs/" + filename).toURI()))) {
            return br.lines().collect(Collectors.toList());
        } catch (IOException | URISyntaxException | NullPointerException e) {
            System.out.println("Cannot read input, check file name!\n" + e.getMessage());
            return List.of();
        }
    }
}
