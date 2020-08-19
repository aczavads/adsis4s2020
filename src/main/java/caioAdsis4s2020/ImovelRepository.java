package caioAdsis4s2020;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImovelRepository {

	private ConnectionManager connectionManager;

	public ImovelRepository(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
		this.createTable();
	}

	private void createTable() {
		PreparedStatement psCreateTable = null;
		try {
			psCreateTable = connectionManager
					.prepareStatement("create table if not exists imovel (" + "id long not null primary key,"
							+ "descricao varchar(255) not null," + "metragem number(9,2) not null" + ")");
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

	public void deleteById(Long id) throws SQLException {

		PreparedStatement psDelete = connectionManager.prepareStatement("delete from imovel where id = ?");
		psDelete.setLong(1, id);

		try {
			psDelete.executeUpdate();
		} finally {
			psDelete.close();
		}
	}

	public Imovel findById(Long id) throws SQLException {
		Imovel found = null;
		PreparedStatement psSelect = connectionManager
				.prepareStatement("select id, descricao, metragem from imovel where id = ?");
		psSelect.setLong(1, id);
		ResultSet rsSelect = psSelect.executeQuery();
		try {
			if (rsSelect.next()) {
				found = new Imovel(rsSelect.getLong("id"), rsSelect.getString("descricao"),
						rsSelect.getDouble("metragem"));
			}
		} finally {
			rsSelect.close();
			psSelect.close();
		}
		return found;
	}

	public Imovel save(Imovel imovel) {
		boolean exists = imovel.getId() != null;
		PreparedStatement psSave = null;
		try {
			if (exists) {
				psSave = connectionManager
						.prepareStatement("update imovel set descricao = ?, metragem = ? where id = ?");
				psSave.setString(1, imovel.getDescricao());
				psSave.setDouble(2, imovel.getMetragem());
				psSave.setLong(3, imovel.getId());
			} else {
				Long id = selectNewId();
				System.out.println("Novo id: " + id);
				psSave = connectionManager
						.prepareStatement("insert into imovel (id, descricao, metragem) values (?,?,?)");
				psSave.setLong(1, id);
				psSave.setString(2, imovel.getDescricao());
				psSave.setDouble(3, imovel.getMetragem());
				imovel = new Imovel(id, imovel.getDescricao(), imovel.getMetragem());
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
		return imovel;
	}

	private Long selectNewId() throws SQLException {
		PreparedStatement psSelect = connectionManager
				.prepareStatement("select coalesce(max(id),0)+1 as newId from imovel");
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

}
