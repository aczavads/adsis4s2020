package aula20200811.atividadeDia07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AppAtividadeDia07 {
	
	//A atividade de vocês consiste elaborar uma aplicação que, a partir de seu método main, 
	//crie uma tabela no banco de dados, 
	//realize a inserção de 2000 registros com dados diferentes, 
	//exclua 10 registros pela sua PK (qualquer um deles), 
	//atualize o primeiro registro e 
	//liste os 30 primeiros registros (ordenados pelo id ascendente).	
	public static void main(String[] args) throws Exception {
		Connection conexão = null;
		try {
			conexão = conectarAoBancoDeDados();
			criarTabelaNoBancoDeDados(conexão);
			inserir2000RegistrosComDadosDiferentes(conexão);
			excluirDezPelaPk(conexão, 1,2,3,4,5,6,7,8,9,11);
			atualizarPrimeiroRegistro(conexão);
			listarPrimeiros30NoConsole(conexão);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conexão != null) {
				conexão.rollback();
				conexão.close();
			}
		}
		System.out.println("Fim.");
	}
	private static void listarPrimeiros30NoConsole(Connection conexão) throws Exception {
		ResultSet resultado = conexão.createStatement()
				.executeQuery("select id, titulo from livro order by id asc limit 30");
		try {
			System.out.println("Listando 30 primeiros...");
			while (resultado.next()) {
				System.out.println(resultado.getInt("id") + " | " + resultado.getString("titulo"));
			}
		} finally {
			resultado.close();
		}		
	}
	private static void atualizarPrimeiroRegistro(Connection conexão) throws Exception {
		int idDoPrimeiro = recuperarIdDoPrimeiro(conexão);
		PreparedStatement update = conexão.prepareStatement("update livro set titulo = ? where id = ?");
		try {
			update.setString(1, "Olha o título atualizado do primeiro!");
			update.setInt(2, idDoPrimeiro);
			update.executeUpdate();
			conexão.commit();
		} finally {
			update.close();
		}
	}
	
	private static int recuperarIdDoPrimeiro(Connection conexão) throws Exception {
		int id = -1;
		ResultSet resultado = conexão.createStatement().executeQuery("select min(id) as idDoPrimeiro from livro"); 
		try {
			if (resultado.next()) {
				return resultado.getInt("idDoPrimeiro");
			}
		} finally {
			resultado.close();
		}
		return id;
	}
	
	private static void excluirDezPelaPk(Connection conexão, int... idsParaExcluir) throws Exception {
		PreparedStatement delete = conexão.prepareStatement("delete from livro where id = ?");
		try {
			for (int idParaExcluir : idsParaExcluir) {
				delete.setInt(1, idParaExcluir);
				delete.executeUpdate();
			}
			conexão.commit();
		} finally {
			delete.close();
		}
		
	}
	private static void inserir2000RegistrosComDadosDiferentes(Connection conexão) throws Exception {
		PreparedStatement insert = conexão.prepareStatement("insert into livro (id, titulo) values (?, ?)");
		try {
			int contador = 1;
			while (contador <= 2000) {
				insert.setInt(1, contador);
				insert.setString(2, "Título " + contador);
				insert.executeUpdate();
				contador++;
			}
			conexão.commit();
		} finally {
			insert.close();
		}
		
	}
	private static void criarTabelaNoBancoDeDados(Connection conexão) throws Exception {
		Statement createTable = conexão.createStatement();
		try {
			createTable.execute("create table if not exists livro ("
					+ "id integer not null primary key, "
					+ "titulo varchar(255) not null)");
			conexão.commit();
		} finally {
			createTable.close();
		}
	}
	
	private static Connection conectarAoBancoDeDados() throws Exception {
		Connection conexão = DriverManager.getConnection("jdbc:h2:mem:atividadeDia07", "sa", "");
		conexão.setAutoCommit(false);		
		return conexão;
	}

}
