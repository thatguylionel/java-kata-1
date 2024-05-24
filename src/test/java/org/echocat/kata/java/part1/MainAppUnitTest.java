package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Magazines;
import org.echocat.kata.java.part1.utils.CsvReader;
import org.echocat.kata.java.part1.utils.LibraryUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.SortedMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainAppUnitTest {
    private static List<Book> books;
    private static List<Magazines> magazines;

    @BeforeClass
    public static void setUp() {
        books = CsvReader.readFromCsvFile("books.csv", parts -> new Book(parts[0], parts[1], parts[2], parts[3]));
        magazines = CsvReader.readFromCsvFile("magazines.csv", parts -> new Magazines(parts[0], parts[1], parts[2], parts[3]));
    }

    @Test
    public void testGetHelloWorldText() {
        assertThat(MainApp.getHelloWorldText(), is("Hello world!"));
    }


    @Test
    public void testPrintAllBooksAndMagazinesSortedByTitle() {
        // Print out all books and magazines with all their details sorted by title. This sort should be done for books and magazines together.
        SortedMap<String, String> items = LibraryUtils.printAllBooksAndMagazinesSortedByTitle(books, magazines);
        Assert.assertTrue("Das Perfekte Dinner. Die besten Rezepte", items.containsKey("Das Perfekte Dinner. Die besten Rezepte"));
        Assert.assertTrue("Das Piratenkochbuch. Ein Spezialitätenkochbuch mit den 150 leckersten Rezepten ", items.containsKey("Das Piratenkochbuch. Ein Spezialitätenkochbuch mit den 150 leckersten Rezepten "));
    }

    @Test
    public void testFindBookOrMagazineByIsbn() {
        Book bookByIsbn = LibraryUtils.findBookOrMagazineByIsbn("5554-5545-4518", books, magazines);
        Assert.assertNotNull(bookByIsbn);
        Assert.assertEquals("5554-5545-4518", bookByIsbn.getIsbn());

        Magazines magazineByIsbn = LibraryUtils.findBookOrMagazineByIsbn("2365-8745-7854", books, magazines);
        Assert.assertNotNull(magazineByIsbn);
        Assert.assertEquals("2365-8745-7854", magazineByIsbn.getIsbn());
    }

    @Test
    public void testFindBooksAndMagazinesByAuthorEmail() {
        String email = "null-walter@echocat.org";
        List<String> booksAndMagazinesByAuthorEmail = LibraryUtils.findBooksAndMagazinesByAuthorEmail(email, books, magazines);
        Assert.assertTrue("Genial italienisch", booksAndMagazinesByAuthorEmail.stream().anyMatch(item -> item.contains("Genial italienisch")));
    }

}
