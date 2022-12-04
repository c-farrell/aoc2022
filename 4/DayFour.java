import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DayFour {

    private record Pair(Range first, Range second){
        public static Pair of(String in){
            final var elves = in.split(",");
            return new Pair(
                    Range.of(elves[0]),
                    Range.of(elves[1])
            );
        }
    }
    private record Range(int low, int high){
        public static Range of(String in){
            final var digits = in.split("-");
            return new Range(
                    Integer.parseInt(digits[0]),
                    Integer.parseInt(digits[1])
                    );
        }
    }

    private static boolean fullyOverlaps(Pair pair){
        return pair.first.low >= pair.second.low && pair.first.high <= pair.second.high ||
                pair.second.low >= pair.first.low && pair.second.high <= pair.first.high;
    }
    public static long solve(List<String> pairs) {
        return pairs.stream().map(Pair::of).filter(DayFour::fullyOverlaps).count();
    }
    public static void main(String[] args) throws IOException {
        var puzzleInput = Files.readAllLines(Path.of("4/in.txt"), StandardCharsets.UTF_8);
        var solution = solve(puzzleInput);
        System.out.println(solution);
    }

}
