package hector_victor.aula20200821;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {
	private Connection conn;
	
	public ConnectionManager() throws SQLException {
		conn = DriverManager.getConnection("jdbc:h2:~/pessoaRep","sa","");
		conn.setAutoCommit(false);
	}

	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}

	public void commit() throws SQLException {
		conn.commit();
	}
	
	

}
