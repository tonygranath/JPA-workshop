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
	TestEntityManager em;

	@BeforeEach
	void setUp() {
	}

	@Test
	void testFindById() {
	//	Details d = dao.create(new Details("a", "b", LocalDate.parse("1900-01-01")));
		dao.findAll().forEach(System.out::println);
	//	System.out.println(d);
		System.out.println("end of test");

	}

	@Test
	void testFindAll() {
	}

	@Test
	void testCreate() {
	}

	@Test
	void testUpdate() {
	}

	@Test
	void testRemove() {
	}
}