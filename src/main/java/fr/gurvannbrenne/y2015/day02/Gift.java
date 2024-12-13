package fr.gurvannbrenne.y2015.day02;

public record Gift(
        Integer l,
        Integer w,
        Integer h
) {
    public static Gift parseLine(String line) {
        String[] chunks = line.split("x");
        Integer l = Integer.valueOf(chunks[0]);
        Integer w = Integer.valueOf(chunks[1]);
        Integer h = Integer.valueOf(chunks[2]);
        return new Gift(l, w, h);
    }

    public int getNecessaryWrappingPaper() {
        int face1 = l() * w();
        int face2 = l() * h();
        int face3 = w() * h();

        return 2 * face1 + 2 * face2 + 2 * face3 + Math.min(Math.min(face1, face2), face3);
    }

    public int getNecessaryRibbon() {
        int length = 2 * l() + 2 * w() + 2 * h() - 2 * Math.max(Math.max(l(), w()), h());
        int bow = l() * w() * h();
        return length + bow;
    }
}
