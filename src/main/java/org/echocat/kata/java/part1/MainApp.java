package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.model.Author;
import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Magazine;
import org.echocat.kata.java.part1.services.AuthorService;
import org.echocat.kata.java.part1.services.BookService;
import org.echocat.kata.java.part1.services.PrintedMediaService;

import java.util.List;
import java.util.Scanner;

import static org.echocat.kata.java.part1.utils.CsvReader.readFromCsvFile;

public class MainApp {
    private static final PrintedMediaService printedMediaService = new PrintedMediaService.PrintedMediaServiceImpl();
    private static final AuthorService authorService = new AuthorService.AuthorServiceImpl();
    private static final BookService bookService = new BookService.BookServiceImpl();

    public static void main(String[] args) {
        List<Book> books = readFromCsvFile("books.csv", parts -> new Book(parts[0], parts[1], parts[2], parts[3]), ";");
        List<Magazine> magazines = readFromCsvFile("magazines.csv", parts -> new Magazine(parts[0], parts[1], parts[2], parts[3]), ";");
        List<Author> authors = readFromCsvFile("authors.csv", parts -> new Author(parts[0], parts[1], parts[2]), ";");
        consoleInterface(books, magazines, authors);
    }

    /**
     * This method provides a console interface for the user to interact with the library system.
     * It provides a menu with options to perform various operations like printing all books and magazines,
     * finding a book or magazine by ISBN, finding all books and magazines by an author's email, and adding a new book.
     * The method takes in lists of books, magazines, and authors as parameters.
     *
     * @param books     A list of Book objects representing all the books in the library.
     * @param magazines A list of Magazine objects representing all the magazines in the library.
     * @param authors   A list of Author objects representing all the authors in the library.
     */
    private static void consoleInterface(List<Book> books, List<Magazine> magazines, List<Author> authors) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        printedMediaService.printAllBooksAndMagazinesSortedByTitle(books, magazines);
                        break;
                    case 2:
                        printedMediaService.findBookOrMagazineByIsbn(scanner, books, magazines);
                        break;
                    case 3:
                        authorService.findBooksAndMagazinesByAuthorEmail(scanner, books, magazines);
                        break;
                    case 4:
                        bookService.addNewBook(scanner, books);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    /**
     * Prints out the menu options for the user
     */
    private static void printMenu() {
        System.out.println("\nLibrary Management System");
        System.out.println("1. Print all books and magazines sorted by title");
        System.out.println("2. Find book or magazine by ISBN");
        System.out.println("3. Find all books and magazines by author's email");
        System.out.println("4. Add a new book (in progress)");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

}
