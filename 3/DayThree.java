import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayThree {

    public static int solve(List<String> rucksacks,int elvesPerGroup) {
        int total = 0;
        Set<Character> currentOverlap = new HashSet<>();
        for(int rucksackIndex = 0; rucksackIndex <= rucksacks.size(); rucksackIndex++){
            if(rucksackIndex % elvesPerGroup == 0){
                if(!currentOverlap.isEmpty())
                    total += itemValue(currentOverlap.stream().findAny().get());
                if(rucksackIndex != rucksacks.size())
                    currentOverlap = beginGroup(rucksacks, rucksackIndex);
                continue;
            }
            Set<Character> rucksack = new HashSet<>();
            for(int i = 0; i < rucksacks.get(rucksackIndex).length(); i++){
                rucksack.add(rucksacks.get(rucksackIndex).charAt(i));
            }
            currentOverlap.retainAll(rucksack);
        }
        return total;
    }

    private static Set<Character> beginGroup(List<String> rucksacks, int rucksackIndex) {
        Set<Character> currentOverlap = new HashSet<>();
        for(int i = 0; i < rucksacks.get(rucksackIndex).length(); i++){
            currentOverlap.add(rucksacks.get(rucksackIndex).charAt(i));
        }
        return currentOverlap;
    }

    private static int itemValue(char item){
        final int offset = Character.isLowerCase(item)? 9 : -17;
        return Character.digit(item,Character.MAX_RADIX) - offset;
    }

    public static void main(String[] args) throws IOException {
        var puzzleInput = Files.readAllLines(Path.of("3/in.txt"), StandardCharsets.UTF_8);
        var solution = solve(puzzleInput,3);
        System.out.println(solution);
    }
}
