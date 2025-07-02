package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.Blog;
import riccardogulin.exceptions.NotFoundException;

import java.util.UUID;

public class BlogsDAO {
	private final EntityManager entityManager;

	public BlogsDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Blog newBlog) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(newBlog);
		transaction.commit();
		System.out.println("Il blog " + newBlog.getId() + " è stato salvato!");
	}

	public Blog findById(String blogId) {
		Blog found = entityManager.find(Blog.class, UUID.fromString(blogId));
		if (found == null) throw new NotFoundException(blogId);
		return found;
	}
}
