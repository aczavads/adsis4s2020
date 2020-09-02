package aula20200901.springBoot;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppSpringBoot implements CommandLineRunner {
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) {
		SpringApplication.run(AppSpringBoot.class, args).close();
	}
	@Override
	public void run(String... args) throws Exception {		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		for (int i = 0; i < 10; i++) {
			entityManager.persist(new Livro("Big Java edição " + i,"Horstmann", 125 * i ));
		}
		
		for (Livro livro: entityManager.createQuery("from Livro", Livro.class).getResultList()) {
			System.out.println(livro.toString());
		}
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		System.out.println("Fim.");
	}
/*
	@Override
	public void run(String... args) throws Exception {		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		for (int i = 0; i < 10; i++) {
			entityManager.persist(new Pet("Fiel"));
		}		
		for (Pet pet : entityManager.createQuery("from Pet", Pet.class).getResultList()) {
			System.out.println(pet.toString());
		}
		
		System.out.println(">>>>>>>>>>>");
		System.out.println(entityManager.find(Pet.class, 9L));		
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		System.out.println("Fim.");
	}
*/	
}
