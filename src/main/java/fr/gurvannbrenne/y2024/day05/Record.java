package fr.gurvannbrenne.y2024.day05;

import java.util.List;

public record Record(
        List<Integer> numbers
) {
    boolean respectsConstraints(List<Rule> rules) {
        for (Rule rule : rules) {
            if (numbers.indexOf(rule.a()) > numbers.indexOf(rule.b())) return false;
        }
        return true;
    }

    public void fixWithConstraints(List<Rule> constraints) {
        do {
            for (Rule rule: constraints) {
                int indexA = numbers.indexOf(rule.a());
                int indexB = numbers.indexOf(rule.b());
                if (indexA > indexB) {
                    Integer temp = numbers.get(indexA);
                    numbers.set(indexA, numbers.get(indexB));
                    numbers.set(indexB, temp);
                }
            }
        } while (!respectsConstraints(constraints));
    }
}
