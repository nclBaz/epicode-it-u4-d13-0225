package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.User;
import riccardogulin.exceptions.NotFoundException;

import java.util.UUID;

public class UsersDAO {
	private final EntityManager entityManager;

	public UsersDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(User newUser) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(newUser);
		transaction.commit();
		System.out.println("L'utente " + newUser.getUserId() + " è stato salvato!");
	}

	public User findById(String userId) {
		User found = entityManager.find(User.class, UUID.fromString(userId));
		if (found == null) throw new NotFoundException(userId);
		return found;
	}
}
