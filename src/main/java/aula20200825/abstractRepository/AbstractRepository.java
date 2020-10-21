package aula20200825.abstractRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Generics: ENTIDADE é um tipo que será definido pela subclasse que extends de AbstractRepository
public abstract class AbstractRepository<ENTITY extends AbstractEntity> {
	private ConnectionManager connectionManager;

	public AbstractRepository(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
		this.createTable();		
	}
	
	
	public abstract String getUpdateCommand();
	public abstract String getInsertCommand();
	public abstract void setUpdateParametersFromEntity(PreparedStatement ps, ENTITY entity) throws SQLException;
	public abstract ENTITY setInsertParametersFromEntity(PreparedStatement ps, Long newId, ENTITY entity) throws SQLException;
	public abstract String deleteCommand();
	public ENTITY save(ENTITY entity) {
		boolean exists = entity.getId() != null;
		PreparedStatement psSave = null;
		try {
			if (exists) {
				psSave = connectionManager.prepareStatement(getUpdateCommand());
				setUpdateParametersFromEntity(psSave, entity);
			} else {
				Long id = selectNewId();
				System.out.println("Novo id: " + id);
				psSave = connectionManager.prepareStatement(getInsertCommand());
				entity = setInsertParametersFromEntity(psSave, id, entity);
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
		return entity;
	}
	
	
	

	public abstract String getTableName();
	
	private Long selectNewId() throws SQLException {
		PreparedStatement psSelect = connectionManager.prepareStatement("select coalesce(max(id),0)+1 as newId from " + getTableName() );
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
	
	
	
	public abstract String getFindByIdCommand();
	public abstract ENTITY mapResultSetToEntity(ResultSet rs) throws SQLException;
	
	public ENTITY findById(Long id) throws SQLException {
		ENTITY found = null;
		PreparedStatement psSelect = connectionManager.prepareStatement(getFindByIdCommand());
		psSelect.setLong(1, id);
		ResultSet rsSelect = psSelect.executeQuery();
		try {
			if (rsSelect.next()) {
				found = mapResultSetToEntity(rsSelect); 
			}
		} finally {
			rsSelect.close();
			psSelect.close();
		}
		return found;
	}
	
	public abstract String getDeleteByIdCommand();

	public void delete(Long id) throws SQLException {
		PreparedStatement psDelete = connectionManager.prepareStatement(getDeleteByIdCommand());
		psDelete.setLong(1, id);
		try {
			psDelete.executeUpdate();
		} finally {
			psDelete.close();
		}
	}
	/*
	{...}
	Exemplo em um CorRepository que extends AbstractRepository<Cor>

	public String getDeleteByIdCommand() {
		return "delete from cor where id = ?";
	}
	*/

	
	public abstract String getCreateTableCommand();

	//Padrão de projeto "Template Method".
	private void createTable() {
		PreparedStatement psCreateTable = null;
		try {
			psCreateTable =  connectionManager.prepareStatement(getCreateTableCommand());
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


}
