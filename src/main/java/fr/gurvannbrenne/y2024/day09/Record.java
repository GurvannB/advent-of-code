package fr.gurvannbrenne.y2024.day09;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private String base;
    private List<Integer> representation;

    public Record(String base) {
        this.base = base;
    }

    private void parseRepresentation() {
        representation = new ArrayList<>();

        int id = 0;
        boolean isFile = true;
        for (char c : base.toCharArray()) {
            Integer occurrencesToAdd = Integer.parseInt(c + "");
            if (isFile) {
                for (int i = 0; i < occurrencesToAdd; i++) {
                    representation.add(id);
                }
                id++;
            } else {
                for (int i = 0; i < occurrencesToAdd; i++) {
                    representation.add(null);
                }
            }
            isFile = !isFile;
        }
    }

    private void compactRepresentation() {
        for (int i = 0; i < representation.size(); i++) {
            if (representation.get(i) == null) {
                int lastDigitIndex = lastDigitIndex();
                if (i < lastDigitIndex) {
                    representation.set(i, representation.get(lastDigitIndex));
                    representation.set(lastDigitIndex, null);
                }
            }
        }
    }

    private int lastDigitIndex() {
        for (int i = representation.size() - 1; i >= 0; i--) {
            if (representation.get(i) != null) {
                return i;
            }
        }
        return -1;
    }

//    private void compactRepresentation2() {
//        List<List<Integer>> chunksIndexes = getChunksIndexes();
//        for (int chunkIndex = 0; chunkIndex < chunksIndexes.size(); chunkIndex++) {
//            List<Integer> chunk = chunksIndexes.get(chunkIndex);
//            for (int i = 0; i < representation.size(); i++) {
//                int nbConsecutiveEmptySpaces = 0;
//                if (representation.get(i) == null) {
//                    while (i + nbConsecutiveEmptySpaces < representation.size() && representation.get(i + nbConsecutiveEmptySpaces) == null) {
//                        nbConsecutiveEmptySpaces++;
//                    }
//                }
//                if (nbConsecutiveEmptySpaces >= chunk.size() && i < chunk.get(chunk.size() - 1)) {
//                    for (int j = 0; j < chunk.size(); j++) {
//                        representation.set(i + j, representation.get(chunk.get(j)));
//                        representation.set(chunk.get(j), null);
//                    }
//                    i += chunk.size() - 1;
//                }
//            }
//        }
//    }
//
//    private List<List<Integer>> getChunksIndexes() {
//        List<List<Integer>> chunksIndexes = new ArrayList<>();
//        Integer currentNumber = null;
//        List<Integer> currentChunk = new ArrayList<>();
//        for (int i = representation.size() - 1; i >= 0; i--) {
//            Integer number = representation.get(i);
//            if (number != null) {
//                if (!Objects.equals(currentNumber, number)) {
//                    if (currentChunk.size() > 0) chunksIndexes.add(currentChunk);
//                    currentNumber = number;
//                    currentChunk = new ArrayList<>();
//                    currentChunk.add(i);
//                } else {
//                    currentChunk.add(i);
//                }
//            }
//        }
//
//        if (currentChunk.size() > 0) chunksIndexes.add(currentChunk);
//        return chunksIndexes;
//    }

    public Long getChecksumPart2() {
        List<File> files = new ArrayList<>();
        List<Integer> spaces = new ArrayList<>();

        for (int i = 0; i < base.length(); i++) {
            if (i % 2 == 0) {
                files.add(new File(i / 2, Integer.parseInt(base.charAt(i) + "")));
            } else {
                spaces.add(Integer.parseInt(base.charAt(i) + ""));
            }
        }
        spaces.add(0);

        for (int fileI = files.size() - 1; fileI >= 0; fileI--) {
            int spaceI = 0;
            File file = files.get(fileI);
            while (spaceI < spaces.size() && spaces.get(spaceI) < file.getRepetitions()) {
                spaceI++;
            }
            if (spaceI < spaces.size() && fileI > spaceI) {
                spaces.set(spaceI, spaces.get(spaceI) - file.getRepetitions());
                spaces.add(spaceI, 0);
                spaces.set(fileI, spaces.get(fileI) + file.getRepetitions() + spaces.get(fileI + 1));
                spaces.remove(fileI + 1);

                files.add(spaceI + 1, file);
                files.remove(fileI + 1);

                fileI++;
            }
        }

        int id = 0;
        Long total = 0L;
        for (int i = 0; i < files.size(); i++) {
            for (int j = 0; j < files.get(i).getRepetitions(); j++) {
                total += (long) files.get(i).getNumber() * id;
                id++;
            }
            for (int j = 0; j < spaces.get(i); j++) {
                id++;
            }
        }
        return total;
    }

    private Long total() {
        int i = 0;
        Long total = 0L;
        while (i < representation.size()) {
            if (representation.get(i) != null) {
                total += representation.get(i) * i;
            }
            i++;
        }

        return total;
    }

    public Long getChecksum() {
        this.parseRepresentation();
        this.compactRepresentation();
        return this.total();
    }
}
