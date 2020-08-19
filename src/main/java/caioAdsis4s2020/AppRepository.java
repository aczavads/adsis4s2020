package caioAdsis4s2020;

import java.sql.SQLException;

public class AppRepository {

	public static void main(String[] args) throws SQLException {
		ConnectionManager connManager = new ConnectionManager();
		try {
			ImovelRepository repo = new ImovelRepository(connManager);

			Imovel apto777b = new Imovel("Apartamento 5011 Ed. Torre Encantada", 125.00);
			apto777b = repo.save(apto777b);
			connManager.commit();

			Long id = apto777b.getId();
			System.out.println("Procurando pelo id: " + id);

			Imovel encontradoPeloId = repo.findById(id);
			System.out.println(encontradoPeloId.toString());

			repo.deleteById(id);
			System.out.println("Resultado do findById(" + id + ") após a exclusão: " + repo.findById(id));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connManager.close();
		}
	}

}
