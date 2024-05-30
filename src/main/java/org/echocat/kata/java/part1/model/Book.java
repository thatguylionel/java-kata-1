package org.echocat.kata.java.part1.model;

public record Book(String title, String isbn, String authors, String description) {
    /**
     * Constructs a Book with specified title, ISBN, authors, and description.
     *
     * @param title       The title of the book, must not be null or empty.
     * @param isbn        The ISBN of the book, must not be null or empty.
     * @param authors     The authors of the book, stored as a string.
     * @param description A description of the book, can be null.
     */
    public Book {
        if (title == null || title.isEmpty() || isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("Title and ISBN must not be null or empty.");
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors='" + authors + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}
