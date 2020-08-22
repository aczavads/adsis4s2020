package AmirAliMohana;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SerieRepository {

	private ConnectionManager connectionManager;

	public SerieRepository(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
		this.createTable();		
	}
		

	private void createTable() {
		PreparedStatement psCreateTable = null;
		try {
			psCreateTable =  connectionManager.prepareStatement("create table if not exists imovel ("
					+ "id long not null primary key,"
					+ "descricao varchar(255) not null,"
					+ "avaliação number(9,2) not null"
					+ ")");
			psCreateTable.executeLargeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (psCreateTable != null) {
				try {
					psCreateTable.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}



	public Serie findById(Long id) throws SQLException {
		Serie found = null;
		PreparedStatement psSelect = connectionManager.prepareStatement("select id, descricao, avaliação from imovel where id = ?");
		psSelect.setLong(1, id);
		ResultSet rsSelect = psSelect.executeQuery();
		try {
			if (rsSelect.next()) {
				found = new Serie(
						rsSelect.getLong("id"), 
						rsSelect.getString("descricao"), 
						rsSelect.getDouble("avaliação")); 
			}
		} finally {
			rsSelect.close();
			psSelect.close();
		}
		return found;
	}

	public Serie save(Serie Serie) {
		boolean exists = Serie.getId() != null;
		PreparedStatement psSave = null;
		try {
			if (exists) {
				psSave = connectionManager.prepareStatement("update Serie set descricao = ?, avaliação = ? where id = ?");
				psSave.setString(1, Serie.getDescrição());
				psSave.setDouble(2, Serie.getAvaliação());
				psSave.setLong(3, Serie.getId());
			} else {
				Long id = selectNewId();
				System.out.println("Novo id: " + id);
				psSave = connectionManager.prepareStatement("insert into Serie (id, descricao, avaliação) values (?,?,?)");
				psSave.setLong(1, id);
				psSave.setString(2, Serie.getDescrição());
				psSave.setDouble(3, Serie.getAvaliação());
				Serie = new Serie(id,  Serie.getDescrição(), Serie.getAvaliação());
			}
			psSave.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				psSave.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Serie;
	}

	private Long selectNewId() throws SQLException {
		PreparedStatement psSelect = connectionManager.prepareStatement("select coalesce(max(id),0)+1 as newId from Serie");
		ResultSet rsSelect = psSelect.executeQuery();
		try {
			if (rsSelect.next()) {
				return rsSelect.getLong("newId");
			}
		} finally {
			rsSelect.close();
			psSelect.close();
		}
		return null;
	}


	public void deleteById(Long id) throws SQLException {
		PreparedStatement psDelete = connectionManager.prepareStatement("delete from Serie where id = ?");
		psDelete.setLong(1, id);
		try {
			psDelete.executeUpdate();
		} finally {
			psDelete.close();
		}		
	}


	public List<Serie> findAll() throws SQLException {
		List<Serie> all = new ArrayList<>();
		PreparedStatement psSelect = connectionManager.prepareStatement("select id, descricao, avaliação from Serie");
		ResultSet rsSelect = psSelect.executeQuery();
		try {
			while (rsSelect.next()) {
				Serie serie1 = new Serie(
						rsSelect.getLong("id"), 
						rsSelect.getString("descricao"), 
						rsSelect.getDouble("avaliação"));
				all.add(serie1);
			}
		} finally {
			rsSelect.close();
			psSelect.close();
		}
		return all;
	}



}	
