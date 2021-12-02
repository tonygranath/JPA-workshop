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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
class AuthorDAORepositoryTest {
	@Autowired
	private TestEntityManager em;
	@Autowired
	private AuthorDAORepository dao;

	private static final String FIRST_NAME = "Författare";
	private static final String LAST_NAME = "Författarsson";
	private Author author = new Author(FIRST_NAME, LAST_NAME);

	@BeforeEach
	void setUp() {
		em.getEntityManager().createNativeQuery("ALTER TABLE author ALTER COLUMN id RESTART WITH 1").executeUpdate();
		dao.create(author);
	}

	@Test
	void findByName() {
		assertEquals(1, dao.findByName("För").size());
	}
}