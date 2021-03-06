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
import se.lexicon.tonygranath.jpaworkshop.model.Details;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
class DetailsDAORepositoryTest {
	@Autowired
	private DetailsDAORepository dao;
	@Autowired
	private TestEntityManager em;

	private static final String EMAIL = "test@email.se";
	private static final String NAME = "Test Name";
	private static final LocalDate BIRTHDATE = LocalDate.parse("1999-05-05");
	private Details testDetails = new Details(EMAIL, NAME, BIRTHDATE);

	@BeforeEach
	void setUp() {
		em.getEntityManager().createNativeQuery("ALTER TABLE details ALTER COLUMN id RESTART WITH 1").executeUpdate();
		dao.create(new Details(EMAIL, NAME, BIRTHDATE));
		dao.create(new Details("asdf@asdf.se", "Full Name", LocalDate.parse("1980-01-01")));
	}

	@Test
	void testFindById() {
		assertEquals(EMAIL, dao.findById(1).getEmail());
	}

	@Test
	void testFindAll() {
		assertEquals(2, dao.findAll().size());
	}

	@Test
	void testUpdate() {
		testDetails = dao.findById(1);
		String newName = "New Name";
		testDetails.setName(newName);
		dao.update(testDetails);
		assertEquals(newName, dao.findById(testDetails.getDetailsId()).getName());
	}

	@Test
	void testRemove() {
		dao.remove(1);
		dao.remove(2);
		assertEquals(0, dao.findAll().size());
	}
}