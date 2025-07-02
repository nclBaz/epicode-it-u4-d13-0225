package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.Category;
import riccardogulin.exceptions.NotFoundException;

import java.util.UUID;

public class CategoriesDAO {
	private final EntityManager entityManager;

	public CategoriesDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Category newCategory) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(newCategory);
		transaction.commit();
		System.out.println("La categoria " + newCategory.getId() + " è stata salvata!");
	}

	public Category findById(String categoryId) {
		Category found = entityManager.find(Category.class, UUID.fromString(categoryId));
		if (found == null) throw new NotFoundException(categoryId);
		return found;
	}
}
