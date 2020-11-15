package AmirAliMohana;


import java.sql.SQLException;
import java.util.List;

public class AppReposi {

	public static void main(String[] args) throws SQLException {
		ConnectionManager connManager = new ConnectionManager();
		try {
			SerieRepository repo = new SerieRepository(connManager);
			
			Serie Supernatural = new Serie("Supernatural, 15 temporadas", 94.00);
			Supernatural = repo.save(Supernatural);
			connManager.commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			connManager.close();
		}
	}

}
