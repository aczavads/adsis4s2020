package caioAdsis4s2020_08_21;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CarroRepository {
	private ConnectionManager connectionManager;

	public CarroRepository(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
		this.createTable();
	}

	private void createTable() {
		PreparedStatement psCreateTable = null;
		try {
			psCreateTable = connectionManager
					.prepareStatement("create table if not exists carro (" + "id long not null primary key,"
							+ "modelo varchar(255) not null," + "kilometragem number(9,2) not null" + ")");
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

	public Carro findById(Long id) throws SQLException {
		Carro found = null;
		PreparedStatement psSelect = connectionManager
				.prepareStatement("select id, modelo, kilometragem from carro where id = ?");
		psSelect.setLong(1, id);
		ResultSet rsSelect = psSelect.executeQuery();
		try {
			if (rsSelect.next()) {
				found = new Carro(rsSelect.getLong("id"), rsSelect.getString("modelo"),
						rsSelect.getDouble("kilometragem"));
			}
		} finally {
			rsSelect.close();
			psSelect.close();
		}
		return found;
	}

	public Carro save(Carro carro) {
		boolean exists = carro.getId() != null;
		PreparedStatement psSave = null;
		try {
			if (exists) {
				psSave = connectionManager
						.prepareStatement("update carro set modelo = ?, kilometragem = ? where id = ?");
				psSave.setString(1, carro.getModelo());
				psSave.setDouble(2, carro.getKilometragem());
				psSave.setLong(3, carro.getId());
			} else {
				Long id = selectNewId();
				System.out.println("Novo id: " + id);
				psSave = connectionManager
						.prepareStatement("insert into carro (id, modelo, kilometragem) values (?,?,?)");
				psSave.setLong(1, id);
				psSave.setString(2, carro.getModelo());
				psSave.setDouble(3, carro.getKilometragem());
				carro = new Carro(id, carro.getModelo(), carro.getKilometragem());
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
		return carro;
	}

	private Long selectNewId() throws SQLException {
		PreparedStatement psSelect = connectionManager
				.prepareStatement("select coalesce(max(id),0)+1 as newId from carro");
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
		PreparedStatement psDelete = connectionManager.prepareStatement("delete from carro where id = ?");
		psDelete.setLong(1, id);
		try {
			psDelete.executeUpdate();
		} finally {
			psDelete.close();
		}
	}

	public List<Carro> findAll() throws SQLException {
		List<Carro> all = new ArrayList<>();
		PreparedStatement psSelect = connectionManager.prepareStatement("select id, modelo, kilometragem from carro");
		ResultSet rsSelect = psSelect.executeQuery();
		try {
			while (rsSelect.next()) {
				Carro imóvel = new Carro(rsSelect.getLong("id"), rsSelect.getString("modelo"),
						rsSelect.getDouble("kilometragem"));
				all.add(imóvel);
			}
		} finally {
			rsSelect.close();
			psSelect.close();
		}
		return all;
	}

}