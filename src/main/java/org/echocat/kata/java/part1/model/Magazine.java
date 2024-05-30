package org.echocat.kata.java.part1.model;

public record Magazine(String title, String isbn, String authors, String publishedAt) {
    /**
     * Constructs a Magazine with specified title, ISBN, authors, and published date.
     *
     * @param title       The title of the magazine, must not be null or empty.
     * @param isbn        The ISBN of the magazine, must not be null or empty.
     * @param authors     The authors of the magazine, stored as a string.
     * @param publishedAt The publication date of the magazine, must not be null or empty.
     */
    public Magazine {
        if (title == null || title.isEmpty() || isbn == null || isbn.isEmpty() ||
                publishedAt == null || publishedAt.isEmpty()) {
            throw new IllegalArgumentException("Title, ISBN, and publishedAt must not be null or empty.");
        }
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors='" + authors + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magazine magazine = (Magazine) o;
        return isbn.equals(magazine.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}

