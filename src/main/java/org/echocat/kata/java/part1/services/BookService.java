package org.echocat.kata.java.part1.services;

import org.echocat.kata.java.part1.model.Book;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Interface defining the operations related to managing books within a library system.
 * It includes functionalities to add new books to the system.
 */
public interface BookService {

    /**
     * Prompts the user to enter details for a new book and adds it to the provided list of books.
     *
     * @param scanner
     * @param books
     */
    void addNewBook(Scanner scanner, List<Book> books);

    class BookServiceImpl implements BookService {
        @Override
        public void addNewBook(Scanner scanner, List<Book> books) {
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
}
