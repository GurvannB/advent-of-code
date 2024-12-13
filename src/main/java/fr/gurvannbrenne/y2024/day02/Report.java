package fr.gurvannbrenne.y2024.day02;

import java.util.List;

public class Report {
    private List<Integer> numbers;

    public Report(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public String toString() {
        return numbers.toString();
    }
}
