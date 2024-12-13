package fr.gurvannbrenne.y2024.day11;

public class Stone {
    private Long value;

    public Stone(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String toString() {
        return "Sto: "+value;
    }
}
