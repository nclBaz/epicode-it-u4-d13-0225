package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.BlogsDAO;
import riccardogulin.dao.DocumentsDAO;
import riccardogulin.dao.UsersDAO;
import riccardogulin.entities.Blog;
import riccardogulin.entities.Document;
import riccardogulin.entities.User;

public class Application {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d13pu");

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		UsersDAO ud = new UsersDAO(em);
		DocumentsDAO dd = new DocumentsDAO(em);
		BlogsDAO bd = new BlogsDAO(em);

		User aldo = new User("Aldo", "Baglio");
		User giovanni = new User("Giovanni", "Storti");
		User giacomo = new User("Giacomo", "Poretti");

		/*ud.save(aldo);
		ud.save(giovanni);
		ud.save(giacomo);*/

		// *********************************************** 1 TO 1 *****************************************
		// Document aldoDoc = new Document("1i2oj3oij213", "Italy", aldo);
		// Non posso usare aldo in questo perché non fa parte del Persistence Context è un oggetto Java normalissimo ( non ha neanche l'ID!!!)
		// Per passare al document l'oggetto da collegare devo usare o un oggetto sul quale è stato fatto un persist() oppure un oggetto letto da DB

		User aldoFromDb = ud.findById("ff1ff0bf-0caf-4da4-876d-e05134c432c9");
		Document aldoDoc = new Document("1i2oj3oij213", "Italy", aldoFromDb);
		// dd.save(aldoDoc);
		System.out.println(aldoFromDb.getDocument());

		// *********************************************** 1 TO MANY *****************************************

		User giovaFromDB = ud.findById("ed8c82a3-790f-4d11-9664-8884d49be3b3");

		Blog react = new Blog("Postgres", "E' backend ma comunque non bellissimo!", giovaFromDB);
		// bd.save(react);

		Blog javaFromDB = bd.findById("1e112c91-40fa-4210-8fd6-2f55af20be9d");
		System.out.println(javaFromDB);

		System.out.println("--------------------------- BIDIREZIONALITA' -----------------------");
		aldoFromDb.getBlogs().forEach(System.out::println);


		em.close();
		emf.close();
	}
}
