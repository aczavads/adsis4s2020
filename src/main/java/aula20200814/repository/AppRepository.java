package aula20200814.repository;

import java.sql.SQLException;

public class AppRepository {

	public static void main(String[] args) throws SQLException {
		ConnectionManager connManager = new ConnectionManager();
		try {
			ImóvelRepository repo = new ImóvelRepository(connManager);
			
			Imóvel apto777b = new Imóvel("Apartamento 5011 Ed. Torre Encantada", 125.00);
			apto777b = repo.save(apto777b);
			connManager.commit();
			
			Long id = apto777b.getId();
			System.out.println("Procurando pelo id: " + id);

			Imóvel encontradoPeloId = repo.findById(id);
			System.out.println(encontradoPeloId.toString());			
			
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			connManager.close();
		}
	}

}
