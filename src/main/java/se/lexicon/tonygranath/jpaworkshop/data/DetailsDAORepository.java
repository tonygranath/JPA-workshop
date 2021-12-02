package se.lexicon.tonygranath.jpaworkshop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.tonygranath.jpaworkshop.model.Details;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@Transactional
public class DetailsDAORepository extends GenericDAORepository<Details, Integer> {
	@PersistenceContext
	private final EntityManager entityManager;

	@Autowired
	public DetailsDAORepository(EntityManager em) {
		super(em, Details.class);
		entityManager = em;
	}

	/*
	@Autowired
	public DetailsDAORepository(EntityManager em) {
		entityManager = em;
	}

	@Override
	public Details findById(Integer id) {
		if (id == null)
			throw new IllegalArgumentException("id was null.");
		return entityManager.find(Details.class, id);
	}

	@Override
	public Collection<Details> findAll() {
		return entityManager.createQuery("SELECT d FROM Details d", Details.class)
				.getResultList();
	}

	@Override
	public Details create(Details details) {
		if (details == null)
			throw new IllegalArgumentException("details was null.");
		entityManager.persist(details);
		return details;
	}

	@Override
	public Details update(Details details) {
		if (details == null)
			throw new IllegalArgumentException("details was null.");
		return entityManager.merge(details);
	}

	@Override
	public void remove(Integer id) {
		if (id == null)
			throw new IllegalArgumentException("id was null.");
		entityManager.remove(findById(id));
	} */
}
