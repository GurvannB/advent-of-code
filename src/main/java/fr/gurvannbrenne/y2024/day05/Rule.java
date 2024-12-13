package fr.gurvannbrenne.y2024.day05;

import java.util.List;

public record Rule(
        Integer a,
        Integer b
) {
    public boolean implyTwoNumbers(List<Integer> numbers) {
        return numbers.contains(a) && numbers.contains(b);
    }
}
