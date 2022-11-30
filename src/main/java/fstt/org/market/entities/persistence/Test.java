package fstt.org.market.entities.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {

	public static void main(String[] args) {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("unit");
				EntityManager em = emf.createEntityManager();
				em.getTransaction().begin();
				Client etudiant = new Client(1L,"Test", "Boukhalef", "9707", "Tanger");
				
				System.out.println("COMIITING");
				em.persist(etudiant);
				em.getTransaction().commit();

	}

}