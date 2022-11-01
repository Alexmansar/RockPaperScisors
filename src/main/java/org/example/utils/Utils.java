package org.example.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

@Slf4j
public class Utils {

    public static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static String getString() {
        try {
            return READER.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getInt() {
        int number;
        try {
            number = Integer.parseInt(getString());
        } catch (NumberFormatException e) {
            log.error("incorrect value {}", e.getMessage());
            System.out.println("try again " + e.getMessage());
            return getInt();
        }
        return number;
    }

    public static String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return "";
        word = word.toLowerCase(Locale.ROOT);
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}