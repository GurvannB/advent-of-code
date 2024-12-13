package fr.gurvannbrenne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Common {
    public static <T> List<T> readFile(String filename, BiFunction<String, Integer, T> parser) {
        List<T> lines = new ArrayList<>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int row = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                lines.add(parser.apply(data, row));
                row++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        return lines;
    }

    public static <T> List<T> readFile(String filename, Function<String, T> parser) {
        return readFile(filename, (s, i) -> parser.apply(s));
    }

    public static List<String> readFile(String filename) {
        return readFile(filename, s -> s);
    }
}
