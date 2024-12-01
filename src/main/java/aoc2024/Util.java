package aoc2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static List<String> getInput(String path) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(Util.class.getResource(path).toURI()))) {
            return br.lines().collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace(System.err);
            return List.of();
        }
    }
}
