package org.echocat.kata.java.part1.utils;

import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Magazines;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LibraryUtils {
    public static <T> T findBookOrMagazineByIsbn(String isbn, List<Book> books, List<Magazines> magazines) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return (T) book;
            }
        }

        for (Magazines magazine : magazines) {
            if (magazine.getIsbn().equals(isbn)) {
                return (T) magazine;
            }
        }

        return null;
    }


    public static List<String> findBooksAndMagazinesByAuthorEmail(String email, List<Book> books, List<Magazines> magazines) {
        List<String> items = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthors().contains(email)) {
                items.add(book.getTitle());
            }
        }
        for (Magazines magazine : magazines) {
            if (magazine.getAuthors().contains(email)) {
                items.add(magazine.getTitle());
            }
        }
        return items;
    }

    public static SortedMap<String, String> printAllBooksAndMagazinesSortedByTitle(List<Book> books, List<Magazines> magazines) {
        SortedMap<String, String> sortedItems = new TreeMap<>();

        for (Book book : books) {
            sortedItems.put(book.getTitle(), book.toString());
        }

        for (Magazines magazine : magazines) {
            sortedItems.put(magazine.getTitle(), magazine.toString());
        }

        return sortedItems;
    }

    public static void addNewBook(Scanner scanner, List<Book> books) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        Book newBook = new Book(title, isbn, author, description);
        books.add(newBook);

        try (FileWriter writer = new FileWriter("src/main/resources/books.csv", true)) {
            writer.write(String.format("%s;%s;%s;%s\n", title, author, isbn, description));
            System.out.println("Book added and saved to CSV file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to books CSV file: " + e.getMessage());
        }
    }
}
