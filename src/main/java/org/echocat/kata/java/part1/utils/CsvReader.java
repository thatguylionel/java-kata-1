package org.echocat.kata.java.part1.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class CsvReader {
    /**
     * Reads from a CSV file and converts each row into an object of type T.
     *
     * @param fileName name of file
     * @param mapper   Function to dynamically map columns in, expecting same object out
     * @param <T>      Generic object, to allow reusability
     * @return Generic list of objects, based on program requirements
     */
    public static <T> List<T> readFromCsvFile(String fileName, Function<String[], T> mapper, String delimiter) {
        List<T> items = new ArrayList<>();
        try (InputStream inputStream = CsvReader.class.getClassLoader().getResourceAsStream(fileName)) {
            assert inputStream != null;
            try (Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNextLine()) {
                    scanner.nextLine(); // Skip header row
                }
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(delimiter);
                    try {
                        items.add(mapper.apply(parts));
                    } catch (Exception e) {
                        throw new RuntimeException("Error processing line: " + line, e);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("File not found or cannot be read: " + fileName, e);
        }
        return items;
    }
}
