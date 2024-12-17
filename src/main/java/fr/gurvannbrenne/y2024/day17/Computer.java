package fr.gurvannbrenne.y2024.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Computer {
    private Long registerA;
    private Long registerB;
    private Long registerC;
    private List<Long> program;

    private Computer() {
    }

    private Computer(Long registerA, Long registerB, Long registerC, List<Long> program) {
        this.registerA = registerA;
        this.registerB = registerB;
        this.registerC = registerC;
        this.program = program;
    }

    public static Computer parse(String line1, String line2, String line3, String line4) {
        Computer computer = new Computer();
        computer.registerA = Long.parseLong(line1.substring(12));
        computer.registerB = Long.parseLong(line2.substring(12));
        computer.registerC = Long.parseLong(line3.substring(12));
        computer.program = new ArrayList<>();

        for (String chunk : line4.substring(9).split(",")) {
            computer.program.add(Long.parseLong(chunk));
        }
        return computer;
    }

    public String toString() {
        return "Register A: " + registerA + "\n"
                + "Register B: " + registerB + "\n"
                + "Register C: " + registerC + "\n"
                + "\n"
                + "Program: " + program.toString().replaceAll("[\\[\\] ]", "") + "\n";
    }

    public Long getComboOperand(Long operand) {
        return switch ((int) operand.longValue()) {
            case 0, 1, 2, 3 -> operand;
            case 4 -> registerA;
            case 5 -> registerB;
            case 6 -> registerC;
            default -> null;
        };
    }

    public void adv(Long operand) {
        registerA = (long) Math.floor(registerA / Math.pow(2, getComboOperand(operand)));
    }

    public void bxl(Long operand) {
        registerB = bitwiseXor(registerB, operand);
    }

    public void bst(Long operand) {
        registerB = getComboOperand(operand) % 8;
    }

    public void bxc() {
        registerB = bitwiseXor(registerB, registerC);
    }

    public long out(Long operand) {
        return getComboOperand(operand) % 8;
    }

    public void bdv(Long operand) {
        registerB = (long) Math.floor(registerA / Math.pow(2, getComboOperand(operand)));
    }

    public void cdv(Long operand) {
        registerC = (long) Math.floor(registerA / Math.pow(2, getComboOperand(operand)));
    }

    public static long bitwiseXor(Long a, Long b) {
        String binAString = Integer.toBinaryString(a.intValue());
        String binBString = Integer.toBinaryString(b.intValue());
        StringBuilder result = new StringBuilder();
        int maxLength = Math.max(binAString.length(), binBString.length());
        for (int i = 1; i <= maxLength; i++) {
            int bitAI = binAString.length() - i;
            int bitBI = binBString.length() - i;
            char bitA = bitAI >= 0 ? binAString.charAt(bitAI) : '0';
            char bitB = bitBI >= 0 ? binBString.charAt(bitBI) : '0';
            result.append(Objects.equals(bitA, bitB) ? '0' : '1');
        }
        return Long.parseLong(Long.toString(Long.parseLong(result.reverse().toString(), 2), 10));
    }

    public List<Long> getProgramOutput() {
        List<Long> stackTrace = new ArrayList<>();
        int instructionPointer = 0;

        while (instructionPointer < program.size()) {
            int operation = program.get(instructionPointer).intValue();
            switch (operation) {
                case 0:
                    adv(program.get(instructionPointer + 1));
                    instructionPointer += 2;
                    break;
                case 1:
                    bxl(program.get(instructionPointer + 1));
                    instructionPointer += 2;
                    break;
                case 2:
                    bst(program.get(instructionPointer + 1));
                    instructionPointer += 2;
                    break;
                case 3:
                    if (registerA == 0) {
                        instructionPointer += 2;
                        break;
                    }
                    instructionPointer = program.get(instructionPointer + 1).intValue();
                    break;
                case 4:
                    bxc();
                    instructionPointer += 2;
                    break;
                case 5:
                    stackTrace.add(out(program.get(instructionPointer + 1)));
                    instructionPointer += 2;
                    break;
                case 6:
                    bdv(program.get(instructionPointer + 1));
                    instructionPointer += 2;
                    break;
                case 7:
                    cdv(program.get(instructionPointer + 1));
                    instructionPointer += 2;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return stackTrace;
    }

    public Long solvePartTwo() {
//        Integer registerA = 0;
//        Integer registerB = null;
//        Integer registerC = null;
//
//        int idxBoucle = program.size() - 2;
//
//        Computer computer;
//        do {
//
//
//            computer = new Computer(0L, 0L, 0L, program);
//        } while (computer.getProgramOutput() != program);

        /*



         */

        List<Long> stackTrace = new ArrayList<>();
        int instructionPointer = program.size() - 1;
        int boucleIdx;
        while (stackTrace != program) {
            int instruction = program.get(instructionPointer).intValue();
            long operand = program.get(instructionPointer+1);
            switch (instruction) {
                case 0:
                    registerA = (long) Math.floor(registerA * Math.pow(2, getComboOperand(operand)));
                    adv(program.get(instructionPointer + 1));
                    instructionPointer -= 2;
                    break;
                case 1:
                    registerB = bitwiseXor(registerB, operand);
                    instructionPointer -= 2;
                    break;
                case 2:
                    registerB = getComboOperand(operand) + 8;
                    instructionPointer -= 2;
                    break;
                case 3:
                    boucleIdx = instructionPointer - 2;
//                    if (registerA == 0) {
//                        instructionPointer += 2;
//                        break;
//                    }
//                    instructionPointer = program.get(instructionPointer + 1).intValue();
                    break;
                case 4:
                    bxc();
                    instructionPointer -= 2;
                    break;
                case 5:
                    stackTrace.add(out(program.get(instructionPointer + 1)));
                    instructionPointer -= 2;
                    break;
                case 6:
                    registerB = (long) Math.floor(registerA * Math.pow(2, getComboOperand(operand)));
                    instructionPointer -= 2;
                    break;
                case 7:
                    registerC = (long) Math.floor(registerA * Math.pow(2, getComboOperand(operand)));
                    instructionPointer -= 2;
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            System.out.println(instruction);
        }
        return null;
    }
}
