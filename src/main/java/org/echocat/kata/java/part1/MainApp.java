package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.model.Authors;
import org.echocat.kata.java.part1.model.Book;
import org.echocat.kata.java.part1.model.Magazines;
import org.echocat.kata.java.part1.utils.LibraryUtils;

import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;

import static org.echocat.kata.java.part1.utils.CsvReader.readFromCsvFile;

public class MainApp {

    public static void main(String[] args) {
        List<Book> books = readFromCsvFile("books.csv", parts -> new Book(parts[0], parts[1], parts[2], parts[3]));
        List<Magazines> magazines = readFromCsvFile("magazines.csv", parts -> new Magazines(parts[0], parts[1], parts[2], parts[3]));
        List<Authors> authors = readFromCsvFile("authors.csv", parts -> new Authors(parts[0], parts[1], parts[2]));
        consoleInterface(books, magazines, authors);
    }

    private static void consoleInterface(List<Book> books, List<Magazines> magazines, List<Authors> authors) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    SortedMap<String, String> items = LibraryUtils.printAllBooksAndMagazinesSortedByTitle(books, magazines);
                    System.out.println("\nAll books and magazines sorted by title:");
                    for (String item : items.values()) {
                        System.out.println(item);
                    }
                    break;
                case 2:
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    Object bookOrMagazineByIsbn = LibraryUtils.findBookOrMagazineByIsbn(isbn, books, magazines);
                    assert bookOrMagazineByIsbn != null;
                    if (bookOrMagazineByIsbn.getClass().equals(Book.class)) {
                        System.out.println("Book: " + bookOrMagazineByIsbn);
                    } else if (bookOrMagazineByIsbn.getClass().equals(Magazines.class)) {
                        System.out.println("Magazine: " + bookOrMagazineByIsbn);
                    }
                    break;
                case 3:
                    System.out.print("Enter Author's Email: ");
                    String email = scanner.nextLine();
                    LibraryUtils.findBooksAndMagazinesByAuthorEmail(email, books, magazines).forEach(System.out::println);
                    break;
                case 4:
                    LibraryUtils.addNewBook(scanner, books);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nLibrary Management System");
        System.out.println("1. Print all books and magazines sorted by title");
        System.out.println("2. Find book or magazine by ISBN");
        System.out.println("3. Find all books and magazines by author's email");
        System.out.println("4. Add a new book (in progress)");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }


    protected static String getHelloWorldText() {
        return "Hello world!";
    }

}
