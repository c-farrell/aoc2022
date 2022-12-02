import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.PriorityQueue;

public class DayOne {

    public static int solve(List<String> items, int numElves) {
        var highest = new PriorityQueue<Integer>();
        int current = 0;

        for (var item : items) {
            if (!item.isEmpty()) {
                current += Integer.parseInt(item);
                continue;
            }
            highest.add(current);
            if(highest.size() > numElves){
                highest.poll();
            }
            current = 0;
        }
        return highest.stream().reduce(Integer::sum).orElse(0);
    }

    public static void main(String[] args) throws IOException {
        var puzzleInput = Files.readAllLines(Path.of("1/in.txt"), StandardCharsets.UTF_8);
        var solution = solve(puzzleInput,3);
        System.out.println(solution);
    }
}
