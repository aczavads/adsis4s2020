package aula20200728.introJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AppIntroJdbc {
	
	public static void main(String[] args) {
		try {
			Connection conexão = DriverManager.getConnection("jdbc:h2:~/teste2","sa","");
			
			conexão.createStatement().execute("create table IF NOT EXISTS aluno  ("
					+ "id integer not null primary key, "
					+ "nome varchar(255) not null unique"
					+ ")");		
			
//			conexão.createStatement().execute(
//					"insert into aluno (id, nome) values (2, 'Novo')");
			PreparedStatement psInsert = conexão.prepareStatement(
					"insert into aluno (id, nome) values (?, ?)");
			psInsert.setInt(1, 1);
			psInsert.setString(2, "teste");
			psInsert.execute();
			psInsert.close();
			
			
//			int quantosForamAtualizados = conexão.createStatement().executeUpdate(
//					"update aluno set nome = 'teste 2' where id = 1");
			PreparedStatement psUpdate = conexão.prepareStatement(
					"update aluno set nome = ? where id = ?");
			psUpdate.setString(1, "Teste 2");
			psUpdate.setInt(2, 1);
			int quantosForamAtualizados = psUpdate.executeUpdate();
			psUpdate.close();
			
			
//			int quantosForamExcluídos = conexão.createStatement().executeUpdate(
//					"delete from aluno where id = 1");
			PreparedStatement psDelete = conexão.prepareStatement(
					"delete from aluno where id = ?");
			psDelete.setInt(1, 1);
			int quantosForamExcluídos  = psDelete.executeUpdate();
			psDelete.close();
			
			System.out.println("Atualizados: " + quantosForamAtualizados);
			System.out.println("Excluídos..: " + quantosForamExcluídos);
			
			//ResultSet resultado = conexão.createStatement().executeQuery("select id, nome from aluno where id = 1 or id = 2");
			PreparedStatement psSelect = conexão.prepareStatement(
					"select id, nome from aluno where id = ? or id = ?");
			psSelect.setInt(1, 1);
			psSelect.setInt(2, 2);
			ResultSet resultado = psSelect.executeQuery();
			
			while (resultado.next()) {
				System.out.println("Aluno: " + resultado.getInt("id")  + "-" + resultado.getString("nome") );
			}			
			resultado.close();
			psSelect.close();
			conexão.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
