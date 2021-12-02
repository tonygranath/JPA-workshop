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
import se.lexicon.tonygranath.jpaworkshop.model.Details;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
class AppUserDAORepositoryTest {
	@Autowired
	private TestEntityManager em;
	@Autowired
	private AppUserDAORepository dao;

	private static final String EMAIL = "test@email.se";
	private static final String NAME = "Test Name";
	private static final LocalDate BIRTHDATE = LocalDate.parse("1999-05-05");
	private Details testDetails = new Details(EMAIL, NAME, BIRTHDATE);

	private static final String USERNAME = "user1";
	private static final String PASSWORD = "password";
	private static final LocalDate REGDATE = LocalDate.now();
	private AppUser testUser = new AppUser(USERNAME, PASSWORD, REGDATE, testDetails);

	@BeforeEach
	void setUp() {
		em.getEntityManager().createNativeQuery("ALTER TABLE appuser ALTER COLUMN id RESTART WITH 1").executeUpdate();
		testUser = dao.create(testUser);
	}

	@Test
	void findByUsername() {
	//	System.out.println(dao.findByUsername(testUser.getUsername()));
	}

	@Test
	void findByName() {
	}

	@Test
	void findByEmail() {
		System.out.println(
				"************************\n"
				+ dao.findByEmail(testUser.getUserDetails().getEmail()).getUsername()
				+ "\n************************"
		);

	}

	@Test
	void findByRegDateBetween() {
	}

	@Test
	void findByRegDateBefore() {
		System.out.println(
				"************************\n"
				+ dao.findByRegDateBefore(LocalDate.parse("2022-01-01"))
				+ "\n************************"
		);
	}

	@Test
	void findByRegDateAfter() {
	}
}