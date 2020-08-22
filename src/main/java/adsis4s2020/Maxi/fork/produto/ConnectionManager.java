package adsis4s2020.Maxi.fork.produto;
import java.sql.*;
public class ConnectionManager {
	private Connection connec;
	
	public ConnectionManager() throws SQLException{
		connec = DriverManager.getConnection("jdbc:h2:~/tb_produto","sa","");
		connec.setAutoCommit(false);
	}
	
	public void close()
	{
		try {
			connec.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	 
	public PreparedStatement preparedStatement(String sql) throws SQLException{
		return connec.prepareStatement(sql);
	}
	public void commit() {
		try {
			connec.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
