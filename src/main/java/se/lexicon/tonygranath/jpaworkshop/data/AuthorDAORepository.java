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

	/*
	@Autowired
	public AuthorDAORepository(EntityManager em) {
		entityManager = em;
	}

	@Override
	public Author findById(Integer id) {
		if (id == null)
			throw new IllegalArgumentException("id was null.");
		return entityManager.find(Author.class, id);
	}

	@Override
	public Collection<Author> findAll() {
		return entityManager.createQuery("SELECT a FROM Author a", Author.class)
				.getResultList();
	}

	@Override
	public Author create(Author author) {
		if (author == null)
			throw new IllegalArgumentException("author was null.");
		entityManager.persist(author);
		return author;
	}

	@Override
	public Author update(Author author) {
		if (author ==null)
			throw new IllegalArgumentException("author was null.");
		return entityManager.merge(author);
	}

	@Override
	public void remove(Integer id) {
		if (id == null)
			throw new IllegalArgumentException("id was null.");
	} */
}
