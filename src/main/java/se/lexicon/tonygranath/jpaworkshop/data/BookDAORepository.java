package se.lexicon.tonygranath.jpaworkshop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.tonygranath.jpaworkshop.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@Transactional
public class BookDAORepository implements GenericCRUD<Book, Integer> {
	@PersistenceContext
	private final EntityManager entityManager;

	@Autowired
	public BookDAORepository(EntityManager em) {
		entityManager = em;
	}

	@Override
	public Book findById(Integer id) {
		if (id == null)
			throw new IllegalArgumentException("id was null");
		return entityManager.find(Book.class, id);
	}

	@Override
	public Collection<Book> findAll() {
		return entityManager.createQuery("SELECT b FROM Book b", Book.class)
				.getResultList();
	}

	@Override
	public Book create(Book book) {
		if (book == null)
			throw new IllegalArgumentException("book was null.");
		entityManager.persist(book);
		return book;
	}

	@Override
	public Book update(Book book) {
		if (book == null)
			throw new IllegalArgumentException("book was null.");
		return entityManager.merge(book);
	}

	@Override
	public void remove(Integer id) {
		if (id == null)
			throw new IllegalArgumentException("id was null.");
		entityManager.remove(findById(id));
	}
}
