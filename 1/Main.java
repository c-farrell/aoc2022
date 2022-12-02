import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static int solve(List<String> items) {
        int highest = 0;
        int current = 0;

        for (var item : items) {
            if (item.isEmpty()) {
                if (current > highest) {
                    highest = current;
                }
                current = 0;
                continue;
            }
            current += Integer.parseInt(item);
        }
        return highest;
    }

    public static void main(String[] args) throws IOException {
        var puzzleInput = Files.readAllLines(Path.of("in.txt"), StandardCharsets.UTF_8);
        var solution = solve(puzzleInput);
        System.out.println(solution);
    }
}
