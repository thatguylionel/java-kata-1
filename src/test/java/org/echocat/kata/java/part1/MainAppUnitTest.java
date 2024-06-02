package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Magazine;
import org.echocat.kata.java.part1.services.AuthorService;
import org.echocat.kata.java.part1.utils.CsvReader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class MainAppUnitTest {
    private static List<Book> books;
    private static List<Magazine> magazines;
    private final AuthorService authorService = new AuthorService.AuthorServiceImpl();


    @BeforeClass
    public static void setUp() {
        books = CsvReader.readFromCsvFile("books.csv", parts -> new Book(parts[0], parts[1], parts[2], parts[3]), ";");
        magazines = CsvReader.readFromCsvFile("magazines.csv", parts -> new Magazine(parts[0], parts[1], parts[2], parts[3]), ";");
    }



}
