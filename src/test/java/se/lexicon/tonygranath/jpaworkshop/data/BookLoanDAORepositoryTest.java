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
import se.lexicon.tonygranath.jpaworkshop.model.AppUser;
import se.lexicon.tonygranath.jpaworkshop.model.Book;
import se.lexicon.tonygranath.jpaworkshop.model.BookLoan;
import se.lexicon.tonygranath.jpaworkshop.model.Details;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
class BookLoanDAORepositoryTest {
	@Autowired
	private TestEntityManager em;
	@Autowired
	private BookLoanDAORepository dao;

	private static final String BOOK_ISBN = "123123123123";
	private static final String BOOK_TITLE = "My Book";
	private static final int BOOK_DAYS = 15;
	private Book book = new Book(BOOK_ISBN, BOOK_TITLE, BOOK_DAYS);

	private static final String USER_DETAILS_EMAIL = "test@email.se";
	private static final String USER_DETAILS_NAME = "Test Name";
	private static final LocalDate USER_DETAILS_BIRTHDATE = LocalDate.parse("1999-05-05");
	private Details testDetails = new Details(USER_DETAILS_EMAIL, USER_DETAILS_NAME, USER_DETAILS_BIRTHDATE);

	private static final String APPUSER_USERNAME = "user1";
	private static final String APPUSER_PASSWORD = "password";
	private static final LocalDate APPUSER_REGDATE = LocalDate.now();
	private AppUser testUser = new AppUser(APPUSER_USERNAME, APPUSER_PASSWORD, APPUSER_REGDATE, testDetails);

	private BookLoan loan = new BookLoan(LocalDate.parse("1900-01-01"), testUser, book);

	@BeforeEach
	void setUp() {
		em.getEntityManager().createNativeQuery("ALTER TABLE book_loan ALTER COLUMN id RESTART WITH 1").executeUpdate();
		dao.create(loan);
	}

	@Test
	void findByAppUser() {
		assertEquals(1, dao.findByAppUser(testUser).size());
	}

	@Test
	void findUnreturnedPastDueDate() {
		assertEquals(1, dao.findUnreturnedPastDueDate().size());
	}

	@Test
	void findByReturnedStatus() {
		assertEquals(0, dao.findByReturnedStatus(true).size());
		assertEquals(1, dao.findByReturnedStatus(false).size());
	}
}