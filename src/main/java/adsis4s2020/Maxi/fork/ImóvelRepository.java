package adsis4s2020.Maxi.fork;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImóvelRepository {

	private ConnectionManager connectionManager;

	public ImóvelRepository(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
		this.createTable();		
	}
		

	private void createTable() {
		PreparedStatement psCreateTable = null;
		try {
			psCreateTable =  connectionManager.prepareStatement("create table if not exists imovel ("
					+ "id long not null primary key,"
					+ "descricao varchar(255) not null,"
					+ "metragem number(9,2) not null"
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



	public Imóvel findById(Long id) throws SQLException {
		Imóvel found = null;
		PreparedStatement psSelect = connectionManager.prepareStatement("select id, descricao, metragem from imovel where id = ?");
		psSelect.setLong(1, id);
		ResultSet rsSelect = psSelect.executeQuery();
		try {
			if (rsSelect.next()) {
				found = new Imóvel(
						rsSelect.getLong("id"), 
						rsSelect.getString("descricao"), 
						rsSelect.getDouble("metragem")); 
			}
		} finally {
			rsSelect.close();
			psSelect.close();
		}
		return found;
	}

	public Imóvel save(Imóvel imóvel) {
		boolean exists = imóvel.getId() != null;
		PreparedStatement psSave = null;
		try {
			if (exists) {
				psSave = connectionManager.prepareStatement("update imovel set descricao = ?, metragem = ? where id = ?");
				psSave.setString(1, imóvel.getDescrição());
				psSave.setDouble(2, imóvel.getMetragem());
				psSave.setLong(3, imóvel.getId());
			} else {
				Long id = selectNewId();
				System.out.println("Novo id: " + id);
				psSave = connectionManager.prepareStatement("insert into imovel (id, descricao, metragem) values (?,?,?)");
				psSave.setLong(1, id);
				psSave.setString(2, imóvel.getDescrição());
				psSave.setDouble(3, imóvel.getMetragem());
				imóvel = new Imóvel(id,  imóvel.getDescrição(), imóvel.getMetragem());
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
		return imóvel;
	}

	private Long selectNewId() throws SQLException {
		PreparedStatement psSelect = connectionManager.prepareStatement("select coalesce(max(id),0)+1 as newId from imovel");
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
		PreparedStatement psDelete = connectionManager.prepareStatement("delete from imovel where id = ?");
		try {
			psDelete.setLong(1, id);
			psDelete.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			psDelete.close();
		}
		
	}



}	
