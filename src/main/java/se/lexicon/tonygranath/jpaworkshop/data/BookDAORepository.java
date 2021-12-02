package se.lexicon.tonygranath.jpaworkshop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.tonygranath.jpaworkshop.model.Author;
import se.lexicon.tonygranath.jpaworkshop.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@Transactional
public class BookDAORepository extends GenericDAORepository<Book, Integer> {
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
	public BookDAORepository(EntityManager em) {
		super(em, Book.class);
		entityManager = em;
	}

	public Book findByIsbn(String isbn) {
		if ((isbn == null) || isbn.equals(""))
			throw new IllegalArgumentException("isbn was null or empty.");

		return entityManager.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class)
				.setParameter("isbn", isbn)
				.getSingleResult();
	}

	public Collection<Book> findByTitle(String title) {
		if ((title == null) || title.equals(""))
			throw new IllegalArgumentException("title was null or empty.");

		return entityManager.createQuery("SELECT b FROM Book b WHERE b.title LIKE :title", Book.class)
				.setParameter("title", title)
				.getResultList();
	}

	public Collection<Book> findByAuthor(Author author) {
		if (author == null)
			throw new IllegalArgumentException("author was null.");
		return entityManager.createQuery("SELECT b FROM Book b WHERE :author MEMBER OF b.authors", Book.class)
				.setParameter("author", author)
				.getResultList();
	}
}
