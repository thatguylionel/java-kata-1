package org.echocat.kata.java.part1.services;

import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Magazine;

import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public interface PrintedMediaService {
    void printAllBooksAndMagazinesSortedByTitle(List<Book> books, List<Magazine> magazines);

    void findBookOrMagazineByIsbn(Scanner scanner, List<Book> books, List<Magazine> magazines);

    class PrintedMediaServiceImpl implements PrintedMediaService {

        @Override
        public void printAllBooksAndMagazinesSortedByTitle(List<Book> books, List<Magazine> magazines) {
            SortedMap<String, String> sortedItems = new TreeMap<>();

            for (Book book : books) {
                sortedItems.put(book.title(), book.toString());
            }

            for (Magazine magazine : magazines) {
                sortedItems.put(magazine.title(), magazine.toString());
            }
            System.out.println("\nAll books and magazines sorted by title:");
            sortedItems.values().forEach(System.out::println);
        }

        @Override
        public void findBookOrMagazineByIsbn(Scanner scanner, List<Book> books, List<Magazine> magazines) {
            System.out.print("Enter ISBN: ");
            String isbn = scanner.nextLine();
            Object bookOrMagazineByIsbn = retrieveItemByIsbn(isbn, books, magazines);
            switch (bookOrMagazineByIsbn) {
                case null -> System.out.println("No book or magazine found with ISBN " + isbn);
                case Book book -> System.out.println("Book: " + bookOrMagazineByIsbn);
                case Magazine magazine -> System.out.println("Magazine: " + bookOrMagazineByIsbn);
                default -> {
                }
            }
        }

        /**
         * Retrieves a book or magazine from the provided lists that matches the given ISBN.
         * This method checks each book and magazine for a match and returns the first match found.
         *
         * @param isbn      the ISBN to search for
         * @param books     the list of books to search
         * @param magazines the list of magazines to search
         * @return the book or magazine that matches the ISBN; returns null if no match is found
         */
        private static <T> T retrieveItemByIsbn(String isbn, List<Book> books, List<Magazine> magazines) {
            for (Book book : books) {
                if (book.isbn().equals(isbn)) {
                    return (T) book;
                }
            }

            for (Magazine magazine : magazines) {
                if (magazine.isbn().equals(isbn)) {
                    return (T) magazine;
                }
            }

            return null;
        }
    }
}
