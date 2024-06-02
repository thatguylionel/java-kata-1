package org.echocat.kata.java.part1.services;

import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Magazine;
import org.echocat.kata.java.part1.services.PrintedMediaService.PrintedMediaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PrintedMediaServiceImplTest {

    private PrintedMediaServiceImpl service;
    private List<Book> books;
    private List<Magazine> magazines;

    @BeforeEach
    public void setUp() {
        service = new PrintedMediaServiceImpl();
        books = Arrays.asList(
                new Book("Book1", "ISBN1", "Author1", "Description1"),
                new Book("Book2", "ISBN2", "Author2", "Description2")
        );
        magazines = Arrays.asList(
                new Magazine("Magazine1", "ISBN3", "Author3", "Description3"),
                new Magazine("Magazine2", "ISBN4", "Author4", "Description4")
        );
    }

    @Test
    public void testPrintAllBooksAndMagazinesSortedByTitle() throws Exception {
        String output = tapSystemOut(() -> service.printAllBooksAndMagazinesSortedByTitle(books, magazines));

        String expectedOutput = """
                All books and magazines sorted by title:
                Book{title='Book1', isbn='ISBN1', authors='Author1', description='Description1'}
                Book{title='Book2', isbn='ISBN2', authors='Author2', description='Description2'}
                Magazine{title='Magazine1', isbn='ISBN3', authors='Author3', publishedAt='Description3'}
                Magazine{title='Magazine2', isbn='ISBN4', authors='Author4', publishedAt='Description4'}
                """;
        assertEquals(expectedOutput.replaceAll("[\r\n]", ""), output.replaceAll("[\r\n]", ""));
    }

    @Test
    public void testFindBookOrMagazineByIsbn() throws Exception {
        String input = "ISBN1\n";
        Scanner scanner = new Scanner(input);

        String output = tapSystemOut(() -> service.findBookOrMagazineByIsbn(scanner, books, magazines));

        String expectedOutput = "Enter ISBN: Book: Book{title='Book1', isbn='ISBN1', authors='Author1', description='Description1'}\r\n";
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testFindBookOrMagazineByIsbnNotFound() throws Exception {
        String input = "ISBN_NOT_FOUND\n";
        Scanner scanner = new Scanner(input);

        String output = tapSystemOut(() -> service.findBookOrMagazineByIsbn(scanner, books, magazines));

        String expectedOutput = "Enter ISBN: No book or magazine found with ISBN ISBN_NOT_FOUND\r\n";
        assertEquals(expectedOutput, output);
    }
}
