package org.echocat.kata.java.part1.services;

import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Magazine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Interface defining the operations for managing and querying author-related data.
 * It provides methods to find books and magazines by an author's email.
 */
public interface AuthorService {
    /**
     * Prompts the user to enter an author's email and prints the titles of all books
     * and magazines authored by that email.
     *
     * @param scanner
     * @param books
     * @param magazines
     */
    void promptAndFindBooksAndMagazinesByAuthorEmail(Scanner scanner, List<Book> books, List<Magazine> magazines);

    class AuthorServiceImpl implements AuthorService {
        public void promptAndFindBooksAndMagazinesByAuthorEmail(Scanner scanner, List<Book> books, List<Magazine> magazines) {
            System.out.print("Enter Author's Email: ");
            String email = scanner.nextLine();
            List<String> items = fetchTitlesByAuthorEmail(email, books, magazines);
            if (items.isEmpty()) {
                System.out.println("No books or magazines found by author's email " + email);
            } else {
                items.forEach(System.out::println);
            }
        }

        /**
         * Finds all books and magazines authored by the given email.
         *
         * @param email     The email of the author to search for.
         * @param books     The list of books to search in.
         * @param magazines The list of magazines to search in.
         * @return A list of titles of books and magazines authored by the given email.
         */
        private List<String> fetchTitlesByAuthorEmail(String email, List<Book> books, List<Magazine> magazines) {
            List<String> items = new ArrayList<>();
            for (Book book : books) {
                if (book.authors().contains(email)) {
                    items.add(book.title());
                }
            }
            for (Magazine magazine : magazines) {
                if (magazine.authors().contains(email)) {
                    items.add(magazine.title());
                }
            }
            return items;
        }
    }
}
