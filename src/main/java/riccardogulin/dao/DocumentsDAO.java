package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.Document;
import riccardogulin.exceptions.NotFoundException;

import java.util.UUID;

public class DocumentsDAO {
	private final EntityManager entityManager;

	public DocumentsDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Document newDocument) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(newDocument);
		transaction.commit();
		System.out.println("Il document " + newDocument.getDocumentId() + " è stato salvato!");
	}

	public Document findById(String documentId) {
		Document found = entityManager.find(Document.class, UUID.fromString(documentId));
		if (found == null) throw new NotFoundException(documentId);
		return found;
	}
}
