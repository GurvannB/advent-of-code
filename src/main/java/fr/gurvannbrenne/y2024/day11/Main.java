package fr.gurvannbrenne.y2024.day11;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static void main(String[] args) {
        List<String> lines = readFile("src/main/resources/y2024/day11/input.txt");
        List<Stone> stones = new ArrayList<>(Arrays.stream(lines.get(0).split(" ")).map((num) -> new Stone(Long.parseLong(num))).toList());

        Long total = 0L;
        Timestamp from = Timestamp.valueOf(LocalDateTime.now());
        Map<String, Long> history = new HashMap<>();
        for (Stone stone : stones) {
            total += totalStones(stone.getValue(), 75, 1L, history);
        }
        Timestamp to = Timestamp.valueOf(LocalDateTime.now());
        System.out.println("Part 2 : " + total);
        System.out.println("Execution time: " + ((double) (to.getTime() - from.getTime()) / 1000) + "s");
    }

    public static Long totalStones(Long stoneValue, int blinks, Long totalStones, Map<String, Long> history) {
        String historyKey = stoneValue + "-" + blinks;
        if (history.containsKey(historyKey)) return history.get(historyKey);

        if (blinks == 0) return totalStones;
        int stoneValueLength = stoneValue.toString().length();
        Long result = null;
        if (stoneValue == 0) {
            result = totalStones(1L, blinks - 1, totalStones, history);
        } else if (stoneValueLength % 2 == 0) {
            Long leftPart = Long.parseLong(stoneValue.toString().substring(0, stoneValueLength / 2));
            Long rightPart = Long.parseLong(stoneValue.toString().substring(stoneValueLength / 2));
            result = totalStones(leftPart, blinks - 1, totalStones, history) + totalStones(rightPart, blinks - 1, totalStones, history);
        } else {
            result = totalStones(stoneValue * 2024, blinks - 1, totalStones, history);
        }
        history.put(historyKey, result);
        return result;
    }
}
