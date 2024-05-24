package org.echocat.kata.java.part1.utils;

import org.echocat.kata.java.part1.MainApp;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class CsvReader {
    //  Read from CSV file. The return type is generic, because we don't know what type of object we will read
    public static <T> List<T> readFromCsvFile(String fileName, Function<String[], T> mapper) {
        List<T> items = new ArrayList<>();
        InputStream inputStream = CsvReader.class.getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new RuntimeException("File not found: " + fileName);
        }

        try (Scanner scanner = new Scanner(inputStream)) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                items.add(mapper.apply(parts));
            }
        }
        return items;
    }
}
