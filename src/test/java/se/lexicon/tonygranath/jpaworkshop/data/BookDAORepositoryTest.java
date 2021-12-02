package se.lexicon.tonygranath.jpaworkshop.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.tonygranath.jpaworkshop.model.Author;
import se.lexicon.tonygranath.jpaworkshop.model.Book;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
class BookDAORepositoryTest {
	@Autowired
	private TestEntityManager em;
	@Autowired
	private BookDAORepository dao;

	private static final String AUTHOR_FIRST_NAME = "Författare";
	private static final String AUTHOR_LAST_NAME = "Författarsson";
	private Author author = new Author(AUTHOR_FIRST_NAME, AUTHOR_LAST_NAME);
	private static final String ISBN = "123456";
	private static final String TITLE = "My Book";
	private static final int DAYS = 15;
	private Book book = new Book(ISBN, TITLE, DAYS);

	@BeforeEach
	void setUp() {
		em.getEntityManager().createNativeQuery("ALTER TABLE book ALTER COLUMN id RESTART WITH 1").executeUpdate();
		book.addAuthor(author);
		book = dao.create(book);
	}

	@Test
	void findByIsbn() {
		assertEquals(book, dao.findByIsbn(ISBN));
	}

	@Test
	void findByTitle() {
		assertEquals(1, dao.findByTitle(TITLE).size());
	}

	@Test
	void findByAuthor() {
		assertEquals(1, dao.findByAuthor(author).size());
	}
}