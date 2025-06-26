package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void setup() {
        library = new Library();
        book1 = new Book("1984", "George Orwell");
        book2 = new Book("Brave New World", "Aldous Huxley");
    }

    @Test
    public void testAddBook() {
        library.addBook(book1);
        assertEquals(1, library.getBookCount());
        assertTrue(library.getBooks().contains(book1));
    }

    @Test
    public void testAddNullBookThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> library.addBook(null));
    }

    @Test
    public void testRemoveBook() {
        library.addBook(book1);
        assertTrue(library.removeBook(book1));
        assertEquals(0, library.getBookCount());
    }

    @Test
    public void testRemoveBookNotInLibrary() {
        assertFalse(library.removeBook(book1));
    }

    @Test
    public void testRemoveNullBookReturnsFalse() {
        assertFalse(library.removeBook(null));
    }

    @Test
    public void testGetBooksReturnsUnmodifiableList() {
        library.addBook(book1);
        List<Book> books = library.getBooks();
        assertThrows(UnsupportedOperationException.class, () -> books.add(book2));
    }

    @Test
    public void testBookToString() {
        assertEquals("\"1984\" by George Orwell", book1.toString());
    }

    @Test
    public void testBookEqualsAndHashCode() {
        Book anotherBook = new Book("1984", "George Orwell");
        assertEquals(book1, anotherBook);
        assertEquals(book1.hashCode(), anotherBook.hashCode());
    }
}
