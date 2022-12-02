import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DayTwo {

    public static int solve(List<String> rounds) {
        int totalScore = 0;

        for (String round : rounds) {
            Shape opponentShape = Shape.of(round.charAt(0));
            Shape playerShape = Shape.of(round.charAt(2));

            totalScore += playerShape.value();
            totalScore += getOutcome(playerShape, opponentShape).value();
        }

        return totalScore;
    }

    public static void main(String[] args) throws IOException {
        var puzzleInput = Files.readAllLines(Path.of("2/in.txt"), StandardCharsets.UTF_8);
        var solution = solve(puzzleInput);
        System.out.println(solution);
    }

    private static Outcome getOutcome(Shape player, Shape opponent) {
        if (player == opponent) {
            return Outcome.Draw;
        }
        if (player == Shape.Rock && opponent == Shape.Scissors ||
                player == Shape.Scissors && opponent == Shape.Paper ||
                player == Shape.Paper && opponent == Shape.Rock) {
            return Outcome.Win;
        }
        return Outcome.Loss;
    }

    private enum Shape {
        Rock, Paper, Scissors;

        public static Shape of(char c) {
            if (c == 'A' || c == 'X') return Rock;
            if (c == 'B' || c == 'Y') return Paper;
            if (c == 'C' || c == 'Z') return Scissors;
            throw new IllegalArgumentException("Invalid Shape");
        }

        public int value() {
            return switch (this) {
                case Rock -> 1;
                case Paper -> 2;
                case Scissors -> 3;
            };
        }
    }

    private enum Outcome {
        Win, Draw, Loss;

        public int value() {
            return switch (this) {
                case Win -> 6;
                case Draw -> 3;
                case Loss -> 0;
            };
        }
    }
}