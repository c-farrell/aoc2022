import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DayFive {

    private record Instruction(int from, int to, int repetitions) {
    }

    private static final List<Deque<Character>> supplies = new ArrayList<>();
    private static final List<Instruction> instructions = new ArrayList<>();
    private static int num_stacks;

    private static void parseInput(List<String> layers) {
        num_stacks = (layers.get(0).length() + 1) / 4;
        for (int i = 0; i < num_stacks; i++) {
            supplies.add(new ArrayDeque<>());
        }

        int currentLayer = 0;

        while (!layers.get(currentLayer).isBlank()) {
            parseStacks(layers.get(currentLayer));
            currentLayer++;
        }

        currentLayer++;

        while (currentLayer < layers.size()) {
            parseInstruction(layers, currentLayer);
            currentLayer++;
        }
    }

    private static void parseStacks(String layer) {
        for (int stack = 0; stack < num_stacks; stack++) {
            final char crate = layer.charAt(stack * 4 + 1);
            if (crate == ' ' || Character.isDigit(crate)) continue;
            supplies.get(stack).addLast(crate);
        }
    }

    private static void parseInstruction(List<String> layers, int currentLayer) {
        var args = layers.get(currentLayer).split(" ");
        Instruction instruction = new Instruction(
                Integer.parseInt(args[3]) - 1,
                Integer.parseInt(args[5]) - 1,
                Integer.parseInt(args[1])
        );
        instructions.add(instruction);
    }

    private static void executeInstructions() {
        for (Instruction instruction : instructions) {
            for (int i = 0; i < instruction.repetitions; i++) {
                supplies.get(instruction.to).push(supplies.get(instruction.from()).pop());
            }
        }
    }

    private static String topOfStacks() {
        StringBuilder solution = new StringBuilder();
        for (Deque<Character> supply : supplies) {
            solution.append(supply.peek());
        }
        return solution.toString();
    }

    public static String solve(List<String> input) {
        parseInput(input);
        executeInstructions();
        return topOfStacks();
    }

    public static void main(String[] args) throws IOException {
        var puzzleInput = Files.readAllLines(Path.of("5/in.txt"), StandardCharsets.UTF_8);
        var solution = solve(puzzleInput);
        System.out.println(solution);
    }
}
