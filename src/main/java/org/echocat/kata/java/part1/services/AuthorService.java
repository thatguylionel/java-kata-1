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
     * Retrieves titles of all books and magazines authored by the given email.
     *
     * @param email
     * @param books
     * @param magazines
     * @return
     */
    List<String> findBooksAndMagazinesByAuthorEmail(String email, List<Book> books, List<Magazine> magazines);

    /**
     * Prompts the user to enter an author's email and prints the titles of all books
     * and magazines authored by that email.
     *
     * @param scanner
     * @param books
     * @param magazines
     */
    void findBooksAndMagazinesByAuthorEmail(Scanner scanner, List<Book> books, List<Magazine> magazines);

    class AuthorServiceImpl implements AuthorService {

        @Override
        public List<String> findBooksAndMagazinesByAuthorEmail(String email, List<Book> books, List<Magazine> magazines) {
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

        @Override
        public void findBooksAndMagazinesByAuthorEmail(Scanner scanner, List<Book> books, List<Magazine> magazines) {
            System.out.print("Enter Author's Email: ");
            String email = scanner.nextLine();
            List<String> items = findBooksAndMagazinesByAuthorEmail(email, books, magazines);
            if (items.isEmpty()) {
                System.out.println("No books or magazines found by author's email " + email);
            } else {
                items.forEach(System.out::println);
            }
        }

    }
}
