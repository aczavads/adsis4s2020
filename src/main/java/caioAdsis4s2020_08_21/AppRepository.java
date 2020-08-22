package caioAdsis4s2020_08_21;

import java.sql.SQLException;
import java.util.List;

public class AppRepository {

	public static void main(String[] args) throws SQLException {
		ConnectionManager connManager = new ConnectionManager();
		
		try {
			CarroRepository reposytorio = new CarroRepository(connManager);
			
			for (int i = 0; i < 10; i++) {
				Carro corsa = new Carro("Corsa 2010", 130000.00);
				corsa = reposytorio.save(corsa);
				connManager.commit();
				
				Long id = corsa.getId();
				System.out.println("Procurando pelo id: " + id);

				
				Carro encontradoPeloId = reposytorio.findById(id);
				System.out.println(encontradoPeloId.toString());
			}
			
			Carro corsa = new Carro("Corsa 2010", 130000.00);
			corsa = reposytorio.save(corsa);
			connManager.commit();
			
			Long id = corsa.getId();
			System.out.println("Procurando pelo id: " + id);

			
			Carro encontradoPeloId = reposytorio.findById(id);
			System.out.println(encontradoPeloId.toString());
			
			reposytorio.deleteById(id);
			System.out.println("Recuperado após exclusão: " + reposytorio.findById(id));
			
			List<Carro> carrosCadastrados = reposytorio.findAll();
			System.out.println("Todos os Carros >>>>");
			for (Carro carro : carrosCadastrados) {
				System.out.println(carro.toString());
			}
			System.out.println("<<<< Fim.");

			
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			connManager.close();
		}
	}

}