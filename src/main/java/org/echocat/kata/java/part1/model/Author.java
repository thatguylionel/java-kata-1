package org.echocat.kata.java.part1.model;

import java.util.Objects;

public record Author(String email, String firstName, String lastName) {
    /**
     * Constructs an Author with specified email, first name, and last name.
     *
     * @param email     the email of the author, must not be null or empty.
     * @param firstName the first name of the author, must not be null or empty.
     * @param lastName  the last name of the author, must not be null or empty.
     */
    public Author {
        if (email == null || email.isEmpty() || firstName == null || firstName.isEmpty()
                || lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Author parameters must not be null or empty.");
        }
    }

    @Override
    public String toString() {
        return "Author{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return email.equals(author.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

