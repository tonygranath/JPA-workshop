package se.lexicon.tonygranath.jpaworkshop.data;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Transactional
public class GenericDAORepository<T, ID> {
	@PersistenceContext
	private final EntityManager entityManager;
	private final Class<T> clazz;

	protected GenericDAORepository(EntityManager em, Class<T> clazz) {
		entityManager = em;
		this.clazz = clazz;
	}

	protected T findById(ID id) {
		if (id == null)
			throw new IllegalArgumentException("id was null.");

		return entityManager.find(clazz, id);
	}

	protected Collection<T> findAll() {
		return entityManager.createQuery("SELECT t FROM " + clazz.getName() + " t", clazz)
				.getResultList();
	}

	protected T create(T t) {
		if (t == null)
			throw new IllegalArgumentException("t was null.");

		entityManager.persist(t);
		entityManager.flush();
		return t;
	}

	protected T update(T t) {
		if (t == null)
			throw new IllegalArgumentException("t was null.");

		return entityManager.merge(t);
	}

	protected void remove(ID id) {
		if (id == null)
			throw new IllegalArgumentException("id was null.");

		entityManager.remove(findById(id));
	}
}
