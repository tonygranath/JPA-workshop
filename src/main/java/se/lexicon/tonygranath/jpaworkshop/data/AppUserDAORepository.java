package se.lexicon.tonygranath.jpaworkshop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.tonygranath.jpaworkshop.model.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Collection;

@Repository
@Transactional
public class AppUserDAORepository extends GenericDAORepository<AppUser, Integer> {
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
	public AppUserDAORepository(EntityManager em) {
		super(em, AppUser.class);
		entityManager = em;
	}

	public AppUser findByUsername(String username) {
		if ((username == null) || username.equals(""))
			throw new IllegalArgumentException("username was null or empty.");
		return entityManager.createQuery("SELECT au FROM AppUser au WHERE au.username = :username", AppUser.class)
				.setParameter("username", username)
				.getSingleResult();
	}

	public Collection<AppUser> findByName(String name) {
		if ((name == null) || name.equals(""))
			throw new IllegalArgumentException("name was null or empty.");
		return entityManager.createQuery("SELECT au FROM AppUser au " +
											"WHERE au.userDetails.name " +
											"LIKE :name", AppUser.class)
				.setParameter("name", "%" + name + "%")
				.getResultList();
	}

	public AppUser findByEmail(String email) {
		if ((email == null) || email.equals(""))
			throw new IllegalArgumentException("email was null or empty.");
		return entityManager.createQuery(
					"SELECT au FROM AppUser au WHERE au.userDetails.email = :email", AppUser.class
				)
				.setParameter("email", email)
				.getSingleResult();
	}

	public Collection<AppUser> findByRegDateBetween(LocalDate min, LocalDate max) {
		if (min == null)
			throw new IllegalArgumentException("min was null.");
		if (max == null)
			throw new IllegalArgumentException("max was null.");

		return entityManager.createQuery(
				"SELECT au FROM AppUser au WHERE au.regDate BETWEEN :min AND :max", AppUser.class
				)
				.setParameter("min", min)
				.setParameter("max", max)
				.getResultList();
	}

	public Collection<AppUser> findByRegDateBefore(LocalDate before) {
		if (before == null)
			throw new IllegalArgumentException("before was null.");

		return entityManager.createQuery("SELECT au FROM AppUser au WHERE au.regDate < :date", AppUser.class)
				.setParameter("date", before)
				.getResultList();
	}

	public Collection<AppUser> findByRegDateAfter(LocalDate after) {
		if (after == null)
			throw new IllegalArgumentException("after was null.");

		return entityManager.createQuery("SELECT au FROM AppUser au WHERE au.regDate > :date", AppUser.class)
				.setParameter("date", after)
				.getResultList();
	}
}
