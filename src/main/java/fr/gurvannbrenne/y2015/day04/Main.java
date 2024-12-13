package fr.gurvannbrenne.y2015.day04;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static fr.gurvannbrenne.Common.readFile;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        partOne();
        partTwo();
    }

    private static void partOne() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        List<String> lines = readFile("src/main/resources/y2015/day04/input.txt");
        String key = lines.get(0);
        int keyIncrement = 0;
        String hash = null;

        MessageDigest md = MessageDigest.getInstance("MD5");
        do {
            keyIncrement++;
            byte[] messageDigest = md.digest((key+keyIncrement).getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashBuilder = new StringBuilder(no.toString(16));
            while (hashBuilder.length() < 32) {
                hashBuilder.insert(0, "0");
            }
            hash = hashBuilder.toString();
        } while (!hash.startsWith("00000"));

        System.out.println("Part 1: "+keyIncrement);
    }

    private static void partTwo() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        List<String> lines = readFile("src/main/resources/y2015/day04/input.txt");
        String key = lines.get(0);
        int keyIncrement = 0;
        String hash = null;

        MessageDigest md = MessageDigest.getInstance("MD5");
        do {
            keyIncrement++;
            byte[] messageDigest = md.digest((key+keyIncrement).getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashBuilder = new StringBuilder(no.toString(16));
            while (hashBuilder.length() < 32) {
                hashBuilder.insert(0, "0");
            }
            hash = hashBuilder.toString();
        } while (!hash.startsWith("000000"));

        System.out.println("Part 1: "+keyIncrement);
    }
}
