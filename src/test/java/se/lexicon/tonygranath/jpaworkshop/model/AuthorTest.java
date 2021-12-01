package se.lexicon.tonygranath.jpaworkshop.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
class AuthorTest {
	private static final String AUTHOR_FIRST_NAME = "Författare";
	private static final String AUTHOR_LAST_NAME = "Författarsson";
	private Set<Book> books = new HashSet<>();
	private Author author = new Author(AUTHOR_FIRST_NAME, AUTHOR_LAST_NAME, books);

	private static final String BOOK_ISBN = "123123123123";
	private static final String BOOK_TITLE = "My Book";
	private static final int BOOK_DAYS = 15;
	private Book book = new Book(BOOK_ISBN, BOOK_TITLE, BOOK_DAYS);

	@Test
	void addBook() {
		author.addBook(book);
		books = author.getWrittenBooks();
		Set<Author> authors = book.getAuthors();
		assertTrue(books.contains(book));
		assertTrue(authors.contains(author));
	}
}