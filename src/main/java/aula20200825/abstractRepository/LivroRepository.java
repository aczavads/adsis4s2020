package aula20200825.abstractRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivroRepository {
	private ConnectionManager connectionManager;

	public LivroRepository(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
		this.createTable();		
	}
		

	private void createTable() {
		PreparedStatement psCreateTable = null;
		try {
			psCreateTable =  connectionManager.prepareStatement("create table if not exists livro ("
					+ "id long not null primary key,"
					+ "titulo varchar(255) not null,"
					+ "autor varchar(255) not null,"
					+ "quantidade_de_paginas integer not null"
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



	public Livro findById(Long id) throws SQLException {
		Livro found = null;
		PreparedStatement psSelect = connectionManager.prepareStatement("select id, titulo, autor, quantidade_de_paginas from livro where id = ?");
		psSelect.setLong(1, id);
		ResultSet rsSelect = psSelect.executeQuery();
		try {
			if (rsSelect.next()) {
				found = new Livro(
						rsSelect.getLong("id"), 
						rsSelect.getString("titulo"), 
						rsSelect.getString("autor"), 
						rsSelect.getInt("quantidade_de_paginas")); 
			}
		} finally {
			rsSelect.close();
			psSelect.close();
		}
		return found;
	}

	public Livro save(Livro livro) {
		boolean exists = livro.getId() != null;
		PreparedStatement psSave = null;
		try {
			if (exists) {
				psSave = connectionManager.prepareStatement("update livro set titulo = ?, autor = ?, quantidade_de_paginas = ? where id = ?");
				psSave.setString(1, livro.getTítulo());
				psSave.setString(2, livro.getAutor());
				psSave.setDouble(3, livro.getQuantidadeDePáginas());
				psSave.setLong(4, livro.getId());
			} else {
				Long id = selectNewId();
				System.out.println("Novo id: " + id);
				psSave = connectionManager.prepareStatement("insert into livro (id, titulo, autor, quantidade_de_paginas) values (?,?,?,?)");
				psSave.setLong(1, id);
				psSave.setString(2, livro.getTítulo());
				psSave.setString(3, livro.getAutor());
				psSave.setDouble(4, livro.getQuantidadeDePáginas());
				livro = new Livro(id,  livro.getTítulo(), livro.getAutor(), livro.getQuantidadeDePáginas());
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
		return livro;
	}

	private Long selectNewId() throws SQLException {
		PreparedStatement psSelect = connectionManager.prepareStatement("select coalesce(max(id),0)+1 as newId from livro");
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
