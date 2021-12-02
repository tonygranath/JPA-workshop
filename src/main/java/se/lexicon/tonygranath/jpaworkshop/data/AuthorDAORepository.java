package se.lexicon.tonygranath.jpaworkshop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.tonygranath.jpaworkshop.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@Transactional
public class AuthorDAORepository extends GenericDAORepository<Author, Integer> {
	/*
	 * Methods inherited from super class:
	 * 		AppUser findById(Integer id)
	 * 		Collection<AppUser> findAll()
	 * 		AppUser create(AppUser appUser)
	 * 		AppUser update(AppUser appUser)
	 * 		void remove(Integer id)
	 */

	@PersistenceContext
	private final EntityManager entityManager;

	@Autowired
	public AuthorDAORepository(EntityManager em) {
		super(em, Author.class);
		entityManager = em;
	}

	public Collection<Author> findByName(String name) {
		if ((name == null) || name.equals(""))
			throw new IllegalArgumentException("name was null or empty.");
		return entityManager.createQuery(
				"SELECT a FROM Author a " +
					"WHERE CONCAT(a.firstName, ' ', a.lastName) " +
					"LIKE :name", Author.class)
				.setParameter("name", "%" + name + "%")
				.getResultList();
	}
}
