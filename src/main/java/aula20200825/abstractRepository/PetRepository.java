package aula20200825.abstractRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PetRepository extends AbstractRepository<Pet> {

	
	public PetRepository(ConnectionManager connectionManager) {
		super(connectionManager);
	}

	@Override
	public String getCreateTableCommand() {
		return "create table if not exists pet (id long not null primary key, nome varchar(255) not null)";
	}

	@Override
	public String getFindByIdCommand() {
		return "select id, nome from pet where id = ?";
	}

	@Override
	public Pet mapResultSetToEntity(ResultSet rs) throws SQLException {
		return new Pet(rs.getLong("id"), rs.getString("nome"));
	}

	@Override
	public String getTableName() {
		return "pet";
	}

	@Override
	public String getUpdateCommand() {
		return "update pet set nome = ? where id = ?";
	}

	@Override
	public String getInsertCommand() {
		return "insert into pet (id, nome) values (?,?)";
	}

	@Override
	public void setUpdateParametersFromEntity(PreparedStatement ps, Pet entity) throws SQLException {
		ps.setString(1, entity.getNome());
		ps.setLong(2, entity.getId());
	}

	@Override
	public Pet setInsertParametersFromEntity(PreparedStatement ps, Long newId, Pet entity) throws SQLException {
		ps.setLong(1, newId);
		ps.setString(2, entity.getNome());
		return new Pet(newId, entity.getNome() );
	}

}
