package org.echocat.kata.java.part1.services;

import org.echocat.kata.java.part1.model.Book;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class.getName());
        private static final String BOOKS_FILE_PATH = "src/main/resources/books.csv";

        @Override
        public void addNewBook(Scanner scanner, List<Book> books) {
            try {
                String title = promptUserForInput(scanner, "Enter title: ");
                String author = promptUserForInput(scanner, "Enter author: ");
                String isbn = promptUserForInput(scanner, "Enter ISBN: ");
                String description = promptUserForInput(scanner, "Enter Description: ");

                Book newBook = new Book(title, isbn, author, description);
                books.add(newBook);

                saveBookToFile(newBook);
                LOGGER.log(Level.INFO, "Book added and saved to CSV file successfully.");

            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error writing to books CSV file", e);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "An error occurred while adding a new book", e);
            }
        }

        /**
         * Prompts the user to enter input with the provided prompt message.
         *
         * @param scanner
         * @param prompt
         * @return the user input
         */
        private String promptUserForInput(Scanner scanner, String prompt) {
            System.out.print(prompt);
            return scanner.nextLine().trim();
        }

        /**
         * Saves the book details to the CSV file.
         *
         * @param book
         * @throws IOException
         */
        private void saveBookToFile(Book book) throws IOException {
            try (FileWriter writer = new FileWriter(BOOKS_FILE_PATH, true)) {
                writer.write(String.format("%s;%s;%s;%s\n", book.title(), book.authors(), book.isbn(), book.description()));
            }
        }
    }
}
