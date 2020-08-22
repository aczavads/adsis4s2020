package aula20200821.repositoryCompleto;

import java.sql.SQLException;
import java.util.List;

public class AppRepository {

	public static void main(String[] args) throws SQLException {
		ConnectionManager connManager = new ConnectionManager();
		try {
			ImóvelRepository repo = new ImóvelRepository(connManager);
			
			Imóvel apto777b = new Imóvel("Apartamento 777b Ed. Torre Encantada", 125.00);
			apto777b = repo.save(apto777b);
			connManager.commit();
			
			Long id = apto777b.getId();
			System.out.println("Procurando pelo id: " + id);

			/*
			Imóvel encontradoPeloId = repo.findById(id);
			System.out.println(encontradoPeloId.toString());
			
			repo.deleteById(id);
			System.out.println("Recuperado após exclusão: " + repo.findById(id));
			*/
			List<Imóvel> imóveisCadastrados = repo.findAll();
			System.out.println("Todos os imóveis >>>>");
			for (Imóvel imóvel : imóveisCadastrados) {
				System.out.println(imóvel.toString());
			}
			System.out.println("<<<< Fim.");

			
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			connManager.close();
		}
	}

}
