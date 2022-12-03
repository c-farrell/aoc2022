import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayThree {

    public static int solve(List<String> rucksacks) {
        int total = 0;
        for(var rucksack : rucksacks){
            final Set<Character> firstCompartment = new HashSet<>();
            final Set<Character> secondCompartment = new HashSet<>();

            for(int i = 0; i < rucksack.length() / 2; i++){
                firstCompartment.add(rucksack.charAt(i));
            }
            for(int i = rucksack.length() / 2 ; i < rucksack.length(); i++){
                secondCompartment.add(rucksack.charAt(i));
            }
            firstCompartment.retainAll(secondCompartment);
            total += itemValue(firstCompartment.stream().findAny().orElseThrow());

        }

        return total;
    }

    private static int itemValue(char item){
        final int offset = Character.isLowerCase(item)? 9 : -17;
        return Character.digit(item,Character.MAX_RADIX) - offset;
    }

    public static void main(String[] args) throws IOException {
        var puzzleInput = Files.readAllLines(Path.of("3/in.txt"), StandardCharsets.UTF_8);
        var solution = solve(puzzleInput);
        System.out.println(solution);
    }
}
