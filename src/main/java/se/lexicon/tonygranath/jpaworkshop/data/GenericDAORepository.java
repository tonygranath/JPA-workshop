package se.lexicon.tonygranath.jpaworkshop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.tonygranath.jpaworkshop.model.Book;
import se.lexicon.tonygranath.jpaworkshop.model.Details;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Collection;

//@Repository
//@Transactional
public class GenericDAORepository<T, ID> {//implements GenericCRUD<T, Integer> {
//	@PersistenceContext
	private final EntityManager entityManager;
	private final Class<T> clazz;

//	@Autowired
	protected GenericDAORepository(EntityManager em, Class<T> clazz) {
		entityManager = em;
		this.clazz = clazz;
	}

//	@Override
	protected T findById(ID id) {
		if (id == null)
			throw new IllegalArgumentException("id was null.");

		//System.out.println(t);
		//Details d = new Details("1", "2", LocalDate.now());
		return entityManager.find(clazz, id);
		//return null;
	}

//	@Override
	protected Collection<T> findAll() {
		return entityManager.createQuery("SELECT t FROM " + clazz.getName() + " t", clazz)
				.getResultList();

	}

//	@Override
	protected T create(T t) {
		if (t == null)
			throw new IllegalArgumentException("t was null.");
		entityManager.persist(t);
		entityManager.flush();
		return t;
	}

//	@Override
	protected T update(T t) {
		if (t == null)
			throw new IllegalArgumentException("t was null.");
		return entityManager.merge(t);
	}

//	@Override
	protected void remove(ID id) {
		if (id == null)
			throw new IllegalArgumentException("id was null.");
		entityManager.remove(findById(id));
	}
}
