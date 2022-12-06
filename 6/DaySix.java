import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DaySix {
    public static Optional<Integer> findStartMarker(String signal, int markerLength) {
        for (int i = markerLength; i <= signal.length(); i++) {
            Set<Character> uniqueCharacters = new HashSet<>();
            for (int j = 0; j < markerLength; j++) {
                uniqueCharacters.add(signal.charAt(i - j));
            }
            if (uniqueCharacters.size() == markerLength) {
                return Optional.of(i + 1);
            }
        }
        return Optional.empty();
    }

    public static int solve(List<String> signals) {
        return findStartMarker(signals.get(0), 4)
                .orElseThrow(() -> new IllegalArgumentException("Signal does not contain start marker"));
    }

    public static void main(String[] args) throws IOException {
        var puzzleInput = Files.readAllLines(Path.of("6/in.txt"), StandardCharsets.UTF_8);
        var solution = solve(puzzleInput);
        System.out.println(solution);
    }
}
