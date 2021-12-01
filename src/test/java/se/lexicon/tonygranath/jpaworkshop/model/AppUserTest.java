package se.lexicon.tonygranath.jpaworkshop.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
class AppUserTest {

	@Test
	void loan() {
		Book book = new Book("123123123", "Test Book", 15);
		Details details = new Details("email@email.com", "Full Name", LocalDate.parse("1980-01-01"));
		AppUser user = new AppUser("username", "password", LocalDate.now(), details);
		BookLoan testLoan = user.loan(book);
		assertNotNull(testLoan);
		assertEquals(user, testLoan.getBorrower());
		assertEquals(book, testLoan.getBook());
	}
}