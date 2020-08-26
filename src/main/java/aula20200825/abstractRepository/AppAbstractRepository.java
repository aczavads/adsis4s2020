package aula20200825.abstractRepository;

import java.sql.SQLException;

public class AppAbstractRepository {

	public static void main(String[] args) throws SQLException {
		ConnectionManager connManager = new ConnectionManager();
		try {
			ImóvelRepository repo = new ImóvelRepository(connManager);			
			Imóvel apto777b = new Imóvel("Apartamento 5011 Ed. Torre Encantada", 125.00);			
			apto777b = repo.save(apto777b);
			
			
			LivroRepository repoLivro = new LivroRepository(connManager);
			Livro bigJava = new Livro("Big Java 5° edição", "Horstmann", 525);
			repoLivro.save(bigJava);
			
			PetRepository repoPet = new PetRepository(connManager);
			Pet fiel = new Pet("Fiel");
			repoPet.save(fiel);
			
			
			System.out.println(repoLivro.findById(1L));
			System.out.println(repoPet.findById(1L));
			connManager.commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			connManager.close();
		}
	}

}
