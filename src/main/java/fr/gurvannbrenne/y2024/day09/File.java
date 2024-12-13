package fr.gurvannbrenne.y2024.day09;

public class File {
    private Integer number;
    private Integer repetitions;

    public File(Integer number, Integer repetitions) {
        this.number = number;
        this.repetitions = repetitions;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<repetitions; i++) {
            sb.append(number);
        }
        return sb.toString();
    }
}
