package fr.gurvannbrenne.y2024.day13;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record ClawMachineRecord(
        Long AX,
        Long AY,
        Long BX,
        Long BY,
        Long TX,
        Long TY
) {

    public static ClawMachineRecord parse(String l1, String l2, String l3) {
        String[] chunksL1 = l1.split(", Y\\+");
        Long AX = Long.parseLong(chunksL1[0].substring(12));
        Long AY = Long.parseLong(chunksL1[1]);

        String[] chunksL2 = l2.split(", Y\\+");
        Long BX = Long.parseLong(chunksL2[0].substring(12));
        Long BY = Long.parseLong(chunksL2[1]);

        String[] chunksL3 = l3.split(", Y=");
        Long TX = Long.parseLong(chunksL3[0].substring(9));
        Long TY = Long.parseLong(chunksL3[1]);
        return new ClawMachineRecord(AX, AY, BX, BY, TX, TY);
    }

    public String toString() {
        return "Button A: X+"+AX()+", Y+"+AY()+"\nButton B: X+"+BX()+", Y+"+BY()+"\nPrize: X="+TX()+",Y="+TY()+"\n";
    }

    public int getTotalToken() {
        for (int i=0; i<=100; i++) {
            for (int j=0; j<=100; j++) {
                if (i*AX() + j*BX() == TX() && i*AY() + j*BY() == TY()) {
                    return i*3+j;
                }
            }
        }
        return -1;
    }

    public long getTotalTokenPart2() {
        Long ax = AX();
        Long ay = AY();
        Long bx = BX();
        Long by = BY();
        Long tx = TX()+10000000000000L;
        Long ty = TY()+10000000000000L;

        BigDecimal[][] matrix = new BigDecimal[][]{
                {new BigDecimal(ax), new BigDecimal(bx), new BigDecimal(tx)},
                {new BigDecimal(ay), new BigDecimal(by), new BigDecimal(ty)},
        };

        matrix[0][1] = matrix[0][1].divide(matrix[0][0], 20, RoundingMode.HALF_UP);
        matrix[0][2] = matrix[0][2].divide(matrix[0][0], 20, RoundingMode.HALF_UP);
        matrix[0][0] = matrix[0][0].divide(matrix[0][0], 20, RoundingMode.HALF_UP);
        matrix[1][1] = matrix[1][1].subtract(matrix[0][1].multiply(matrix[1][0]));
        matrix[1][2] = matrix[1][2].subtract(matrix[0][2].multiply(matrix[1][0]));
        matrix[1][0] = matrix[1][0].subtract(matrix[0][0].multiply(matrix[1][0]));
        matrix[1][2] = matrix[1][2].divide(matrix[1][1], 20, RoundingMode.HALF_UP);
        matrix[1][1] = matrix[1][1].divide(matrix[1][1], 20, RoundingMode.HALF_UP);
        matrix[0][2] = matrix[0][2].subtract(matrix[0][1].multiply(matrix[1][2]));
        matrix[0][1] = matrix[0][1].subtract(matrix[0][1].multiply(matrix[1][1]));

        Double a = matrix[0][2].setScale(8, RoundingMode.HALF_UP).doubleValue();
        Double b = matrix[1][2].setScale(8, RoundingMode.HALF_UP).doubleValue();

        if (a % 1 != 0 || b % 1 != 0) return -1;
        return Math.round(a*3+b);
    }
}
