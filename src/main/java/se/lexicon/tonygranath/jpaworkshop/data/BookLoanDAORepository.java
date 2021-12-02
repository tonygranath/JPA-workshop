package se.lexicon.tonygranath.jpaworkshop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.tonygranath.jpaworkshop.model.AppUser;
import se.lexicon.tonygranath.jpaworkshop.model.BookLoan;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@Transactional
public class BookLoanDAORepository extends GenericDAORepository<BookLoan, Integer> {
	@PersistenceContext
	private final EntityManager entityManager;

	@Autowired
	public BookLoanDAORepository(EntityManager em) {
		super(em, BookLoan.class);
		entityManager = em;
	}

	public Collection<BookLoan> findByAppUser(AppUser user) {
		if (user == null)
			throw new IllegalArgumentException("user was null.");
		return entityManager.createQuery("SELECT bl FROM BookLoan bl WHERE bl.borrower = :user", BookLoan.class)
				.setParameter("user", user)
				.getResultList();
	}

	public Collection<BookLoan> findUnreturnedPastDueDate() {
		return entityManager.createQuery(
				"SELECT bl FROM BookLoan bl " +
					"WHERE bl.returned = FALSE AND bl.dueDate < CURRENT_DATE", BookLoan.class)
				.getResultList();
	}

	public Collection<BookLoan> findByReturnedStatus(boolean returned) {
		return entityManager.createQuery("SELECT bl FROM BookLoan bl WHERE bl.returned = :status", BookLoan.class)
				.setParameter("status", returned)
				.getResultList();
	}



	/*
	@Autowired
	public BookLoanDAORepository(EntityManager em) {
		entityManager = em;
	}

	@Override
	public BookLoan findById(Integer id) {
		if (id == null)
			throw new IllegalArgumentException("id was null.");
		return entityManager.find(BookLoan.class, id);
	}

	@Override
	public Collection<BookLoan> findAll() {
		return entityManager.createQuery("SELECT b FROM BookLoan b", BookLoan.class)
				.getResultList();
	}

	@Override
	public BookLoan create(BookLoan bookLoan) {
		if (bookLoan == null)
			throw new IllegalArgumentException("bookLoan was null.");
		entityManager.persist(bookLoan);
		return bookLoan;
	}

	@Override
	public BookLoan update(BookLoan bookLoan) {
		if (bookLoan == null)
			throw new IllegalArgumentException("bookLoan was null.");
		return entityManager.merge(bookLoan);
	}

	@Override
	public void remove(Integer id) {
		if (id == null)
			throw new IllegalArgumentException("id was null.");
		entityManager.remove(findById(id));
	} */
}
