package se.lexicon.tonygranath.jpaworkshop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.tonygranath.jpaworkshop.model.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@Transactional
public class AppUserDAORepository implements GenericCRUD<AppUser, Integer> {
	@PersistenceContext
	private final EntityManager entityManager;

	@Autowired
	public AppUserDAORepository(EntityManager em) {
		entityManager = em;
	}

	@Override
	public AppUser findById(Integer id) {
		return entityManager.find(AppUser.class, id);
	}

	@Override
	public Collection<AppUser> findAll() {
		return entityManager.createQuery("SELECT a FROM AppUser a", AppUser.class)
				.getResultList();
	}

	@Override
	public AppUser create(AppUser appUser) {
		if (appUser == null)
			throw new IllegalArgumentException("appUser was null.");
		entityManager.persist(appUser);
		return appUser;
	}

	@Override
	public AppUser update(AppUser appUser) {
		if (appUser == null)
			throw new IllegalArgumentException("appUser was null.");
		return entityManager.merge(appUser);
	}

	@Override
	public void remove(Integer id) {
		if (id == null)
			throw new IllegalArgumentException("id was null.");
		entityManager.remove(findById(id));
	}
}
