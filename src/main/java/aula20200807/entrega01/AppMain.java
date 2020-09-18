package aula20200807.entrega01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppMain {

	public static void main(String[] args) throws SQLException {

		criarBanco();
		// a inserçao so funciona uma vez depois da erro mas eu acho que e erro no
		// paramentro, mas quando eu comento aparece o retorno correto
		inserirHistorico();
		deletarDoHistorico();
		atualizacaoNoHistorico();
		listaFuncionario();
	}

	private static void criarBanco() throws SQLException {
		Connection coneccao = null;
		Statement banco = null;
		String nome = "create table IF NOT EXISTS tabela (" + "id integer not null primary key,"
				+ "funcionario varchar(30) not null " + ")";

		try {
			coneccao = new AppMain().Conectar();
			banco = coneccao.createStatement();
			banco.execute(nome);

		} finally {
			banco.close();
		}

	}

	private static void inserirHistorico() throws SQLException {
		Connection coneccao = null;
		PreparedStatement registrarFuncionario = null;
		String nome = "insert into tabela (id, funcionario) values (?,?)";

		try {
			coneccao = new AppMain().Conectar();
			registrarFuncionario = coneccao.prepareStatement(nome);

			for (int contador = 0; contador < 2000; contador++) {
				registrarFuncionario.setInt(1, contador);
				registrarFuncionario.setString(2, "funcionario" + contador);
				registrarFuncionario.execute();
			}
		} finally {
			registrarFuncionario.close();
		}
	}

	private static void deletarDoHistorico() throws SQLException {
		Connection coneccao = null;
		PreparedStatement excluirDoHistorico = null;
		String nome = "delete from tabela where id = ?";
		try {
			coneccao = new AppMain().Conectar();
			excluirDoHistorico = coneccao.prepareStatement(nome);

			for (int i = 1; i <= 10; i++) {
				excluirDoHistorico.setInt(1, i + 2);
				excluirDoHistorico.execute();
			}

			int quantidade = excluirDoHistorico.executeUpdate();
			System.out.println(quantidade);

		} finally {
			excluirDoHistorico.close();
		}

	}

	private static void atualizacaoNoHistorico() throws SQLException {
		Connection coneccao = null;
		PreparedStatement updateNoHistorico = null;
		String sql = "update tabela set funcionario = ? where id = select min(id) from tabela";

		try {
			coneccao = new AppMain().Conectar();
			updateNoHistorico = coneccao.prepareStatement(sql);
			updateNoHistorico.setNString(1, "Calvin");
			updateNoHistorico.execute();

		} finally {
			updateNoHistorico.close();
		}

	}

	private static void listaFuncionario() throws SQLException {
		Connection coneccao = null;
		Statement listar = null;
		ResultSet funcionariosListados = null;
		String nome = "select top 30 id , funcionario from tabela order by id asc";

		try {
			coneccao = new AppMain().Conectar();
			listar = coneccao.createStatement();
			funcionariosListados = listar.executeQuery(nome);

			while (funcionariosListados.next()) {
				System.out.println("id: " + funcionariosListados.getInt("id") + "   nome: "
						+ funcionariosListados.getString("funcionario"));
			}
		} finally {
			listar.close();
			funcionariosListados.close();
		}

	}

	public Connection Conectar() throws SQLException {
		return DriverManager.getConnection("jdbc:h2:~/funcionarios", "sa", "");
	}

}