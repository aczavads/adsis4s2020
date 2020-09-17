package aula20200904.springBoot;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppSpringBoot implements CommandLineRunner {
	@Autowired
	private EntityManagerFactory factory;
	
	
	public static void main(String[] args) {
		SpringApplication.run(AppSpringBoot.class, args).close();		
		System.out.println("Foi.");		
	}


	@Override
	public void run(String... args) throws Exception {
		Produto probook445g7 = new Produto("HP Probook 445 G7");
		
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManager = factory.createEntityManager();
			transaction = entityManager.getTransaction();
					
			transaction.begin();
			entityManager.persist(probook445g7);
			System.out.println("Id do novo produto: " + probook445g7.getId());
			
			System.out.println("Quantidade de produtos cadastrados: " 
					+ entityManager.createQuery("from Produto").getResultList().size());
			
			Produto produtoIdUm = entityManager.find(Produto.class, 1L);
			produtoIdUm.setNome("Novo note! " + System.currentTimeMillis());
			entityManager.persist(produtoIdUm);
			
			System.out.println("Nome do produto 1 alterado: " + entityManager.find(Produto.class, 1L).getNome());
			
			transaction.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (transaction != null) {
				transaction.rollback();
			}
			if (entityManager != null) {
				entityManager.close();
			}			
		}
	}
}
