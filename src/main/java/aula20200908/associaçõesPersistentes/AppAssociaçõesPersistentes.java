package aula20200908.associaçõesPersistentes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppAssociaçõesPersistentes implements CommandLineRunner {
	@Autowired
	private EntityManagerFactory factory;

	public static void main(String[] args) {
		SpringApplication.run(AppAssociaçõesPersistentes.class, args).close();
	}
	
	@Override
	public void run(String... args) throws Exception {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManager = factory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			
			Pessoa uniCesumar = new Juridica("UniCesumar - Universidade Cesumar Ltda.", "UniCesumar", "01.665.001/0001-66", "isenta");
			Pessoa arthur = new Fisica("Arthur Cattaneo Zavadski", "555.441.544-44","5.547.574-4 SSP-PR");
			
			Endereco avGuedner778 = new Endereco(TipoEndereco.AVENIDA, "Guedner", "778");
			Endereco avGuedner888 = new Endereco(TipoEndereco.AVENIDA, "Guedner", "888");
			uniCesumar.addEndereco(avGuedner778);
			uniCesumar.addEndereco(avGuedner888);
			
			//Com o Cascade ALL na associação entre Pessoa e Endereço não é necessário fazer o persist dos endereços, fará automaticamente. 
			//entityManager.persist(avGuedner778);
			//entityManager.persist(avGuedner888);
			entityManager.persist(uniCesumar);
			entityManager.persist(arthur);
			
			
			Pessoa recuperada = entityManager.find(Juridica.class, uniCesumar.getId());
			for (Endereco e : recuperada.getEnderecos()) {
				System.out.println(e);
			}
			
			entityManager.remove(entityManager.find(Juridica.class, 15L));
			
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
