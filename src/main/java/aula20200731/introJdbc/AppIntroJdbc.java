package aula20200731.introJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AppIntroJdbc {
	
	public static void main(String[] args) throws Exception {
		Connection conexão = null; 
		try {
			conexão = abrirConexão();
			criarTabelaAnimalDeEstimação(conexão);
			truncarTabelaAnimaisDeEstimação(conexão);			
			inserirMilAnimaisDeEstimação(conexão);			
			alterarPrimeiroAnimalParaTotó(conexão);			
			alterarSegundoAnimalParaBidu(conexão);			
			
			long quantidadeDeAnimaisAntesDeDeletar = contarAnimaisDeEstimação(conexão);			
			deletarÚltimoAnimalDeEstimação(conexão);
			long quantidadeDeAnimaisDepoisDeDeletar = contarAnimaisDeEstimação(conexão);
			
			System.out.println(
					"Antes: " + quantidadeDeAnimaisAntesDeDeletar + " Depois:" + quantidadeDeAnimaisDepoisDeDeletar);
			
			listarNoConsoleOsAnimaisDeEstimação(conexão);			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexão.close();
		}
		System.out.println("Foi.");
	}

	private static void listarNoConsoleOsAnimaisDeEstimação(Connection conexão) throws Exception {
		Statement recuperarAnimais = null;
		try {
			recuperarAnimais = conexão.createStatement();
			ResultSet resultado = recuperarAnimais.executeQuery("select id, nome from animal_de_estimacao");
			System.out.println("Listando animais de estimação...");
			while (resultado.next()) {
				System.out.println("> " + resultado.getString("nome") + ", id=" + resultado.getLong("id"));
			}		
			System.out.println("Listagem concluída.");
		} finally {
			recuperarAnimais.close();
		}		
	}

	private static void deletarÚltimoAnimalDeEstimação(Connection conexão) throws Exception {
		Statement deletarÚltimo = null;
		try {
			deletarÚltimo = conexão.createStatement();
			deletarÚltimo.execute("delete from animal_de_estimacao where id = (select max(id) from animal_de_estimacao)");
		} finally {
			deletarÚltimo.close();
		}
	}

	private static long contarAnimaisDeEstimação(Connection conexão) throws Exception {
		long quantidadeDeAnimais = 0;
		Statement contarAnimais = null;
		try {
			contarAnimais = conexão.createStatement();
			ResultSet resultado = contarAnimais.executeQuery("select count(*) as quantidade from animal_de_estimacao");
			if (resultado.next()) {
				quantidadeDeAnimais = resultado.getLong("quantidade");
			}		
		} finally {
			contarAnimais.close();
		}
		return quantidadeDeAnimais;
	}

	private static void alterarSegundoAnimalParaBidu(Connection conexão) throws Exception {
		alterarNomeDoAnimal(conexão, 2L, "Bidu");
	}

	private static void alterarPrimeiroAnimalParaTotó(Connection conexão) throws Exception {
		alterarNomeDoAnimal(conexão, 1L, "Totó");
	}
	
	private static void alterarNomeDoAnimal(Connection conexão, Long id, String novoNome) throws Exception {
		PreparedStatement alterarAnimal = null;
		try {
			alterarAnimal = conexão.prepareStatement("update animal_de_estimacao set nome = ? where id = ?");
			alterarAnimal.setLong(2, id);
			alterarAnimal.setString(1, novoNome);			
			alterarAnimal.execute();
		} finally {
			alterarAnimal.close();
		}		
	}

	private static void truncarTabelaAnimaisDeEstimação(Connection conexão) throws Exception {
		Statement truncarTabela = null;
		try {
			truncarTabela = conexão.createStatement();
			truncarTabela.execute("truncate table animal_de_estimacao");
		} finally {
			truncarTabela.close();
		}
	}

	private static void inserirMilAnimaisDeEstimação(Connection conexão) throws Exception {
		PreparedStatement inserirAnimal = null;
		try {
			inserirAnimal = conexão.prepareStatement("insert into animal_de_estimacao (id, nome) values (?,?)");
			for (int contador = 1; contador <= 1000; contador++) {
				inserirAnimal.setLong(1, contador);
				inserirAnimal.setString(2, "Pet " + contador);
				inserirAnimal.execute();
			}
		} finally {
			inserirAnimal.close();
		}		
	}

	private static void criarTabelaAnimalDeEstimação(Connection conexão) throws Exception {
		Statement criarTabela = null;
		try {
			criarTabela = conexão.createStatement();
			criarTabela.execute("create table if not exists animal_de_estimacao ("
					+ "id long not null primary key,"
					+ "nome varchar(255) not null unique"
					+ ")");
		} finally {
			criarTabela.close();
		}
	}

	private static Connection abrirConexão() throws Exception {
		Connection c = DriverManager.getConnection("jdbc:h2:~/pets", "sa", "");
		return c;
	}

}
