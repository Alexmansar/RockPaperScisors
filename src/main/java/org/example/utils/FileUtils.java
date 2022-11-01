package org.example.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class FileUtils {

    public static void clearFile(String string) {
        try {
            new FileOutputStream(string).close();
        } catch (IOException e) {
            log.error("error {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void readFile(String fileName) {
        try (BufferedReader inputStream = new BufferedReader(new FileReader(fileName))) {
            inputStream.lines().forEach(System.out::println);
        } catch (IOException e) {
            log.error("ERROR: " + e.getMessage());
        }
    }
}