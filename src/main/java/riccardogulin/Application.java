package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.BlogsDAO;
import riccardogulin.dao.CategoriesDAO;
import riccardogulin.dao.DocumentsDAO;
import riccardogulin.dao.UsersDAO;
import riccardogulin.entities.Blog;
import riccardogulin.entities.Category;
import riccardogulin.entities.Document;
import riccardogulin.entities.User;

import java.util.ArrayList;

public class Application {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d13pu");

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		UsersDAO ud = new UsersDAO(em);
		DocumentsDAO dd = new DocumentsDAO(em);
		BlogsDAO bd = new BlogsDAO(em);
		CategoriesDAO cd = new CategoriesDAO(em);

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


		// *********************************************** MANY TO MANY *****************************************
		Category category = new Category("Backend");
		Category category1 = new Category("Frontend");
		Category category2 = new Category("OOP");

		/*cd.save(category);
		cd.save(category1);
		cd.save(category2);*/

		// Per assegnare ad un blog tot categorie dobbiamo:
		// 1. Leggo le categorie che mi interessano dal database
		Category backendCatFromDB = cd.findById("3307ce4a-606b-4f06-a5b6-85e4efe4bee3");
		Category oopCatFromDB = cd.findById("6a937dc1-fc1d-4896-bb03-0dadea4f9e3d");
		// 2. Creo una lista contenenente tali categorie per quel blog
		ArrayList<Category> categories = new ArrayList<>();
		categories.add(backendCatFromDB);
		categories.add(oopCatFromDB);
		// 3. Settargliele tramite setter
		javaFromDB.setCategories(categories);
		// 4. Risalvare il Blog
		// bd.save(javaFromDB); // Fare una save su un oggetto proveniente dal db non vuol dire crearne uno nuovo, bensì fare un'UPDATE di quello pre-esistente


		Blog postgresFromDB = bd.findById("733c7d9b-628f-4690-b471-0661befd09bf");
		ArrayList<Category> categories2 = new ArrayList<>();
		categories2.add(backendCatFromDB);
		postgresFromDB.setCategories(categories2);

		// bd.save(postgresFromDB);


		System.out.println("Tutte le categorie del Blog su Java");
		javaFromDB.getCategories().forEach(c -> System.out.println(c.getName()));

		System.out.println("Blog associati alla categoria Backend");
		backendCatFromDB.getBlogsList().forEach(System.out::println);


		em.close();
		emf.close();
	}
}
