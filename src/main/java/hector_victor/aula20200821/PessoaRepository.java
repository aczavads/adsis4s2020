package hector_victor.aula20200821;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaRepository {
	private ConnectionManager conn;
	
	public PessoaRepository(ConnectionManager conn) throws SQLException {
		this.conn = conn;
		this.criarTabela();
	}

	private void criarTabela() throws SQLException {
		PreparedStatement pr = null;
		String sql = "create table if not exists Pessoa ("
				+ "id long not null primary key,"
				+ "nome varchar(255) not null,"
				+ "altura numeric(15.2) not null"
				+ ")";
		try {
			pr = conn.prepareStatement(sql);
			pr.executeUpdate();			
		} finally {
			pr.close();
		}		
	}
	
	public Pessoa findById (Long id) throws SQLException {
		PreparedStatement pr = null;
		ResultSet st = null;
		String sql = "select id,nome,altura from Pessoa where id = ?";
		Pessoa result = null;
		try {
			pr = conn.prepareStatement(sql);
			pr.setLong(1, id);
			st = pr.executeQuery();
			if(st.next()) {
				result = new Pessoa (st.getLong("id"),
						             st.getString("nome"),
						             st.getDouble("altura"));				
			}			
			
		} finally {
			pr.close();
			st.close();
		}		
		return result;		
	}
	
	public Pessoa salvar(Pessoa p)  {
		PreparedStatement pr = null;		
		String sqlUptade = "update Pessoa set nome = ? altura = ? where id = ? ";
		String sqlInsert = "insert into Pessoa (id, nome, altura) values (?,?,?)";
		
		try {
			if(p.getId() != null) {				
				pr = conn.prepareStatement(sqlUptade);				
				pr.setString(1,p.getNome());
				pr.setDouble(2, p.getAltura());
				pr.setLong(3, p.getId());		
			}	
			else
			{				
				Long maxId = selectNewId();
				
				pr = conn.prepareStatement(sqlInsert);
				pr.setLong(1, maxId);
				pr.setString(2, p.getNome());
				pr.setDouble(3, p.getAltura());
				p = new Pessoa(maxId, p.getNome(),p.getAltura());
			}
			pr.executeUpdate();
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				pr.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p;
	}
	
	private Long selectNewId() throws SQLException {
		PreparedStatement pr = null;
		ResultSet st = null;
		Long maxId = null;
		
		String sql = "select coalesce(max(id),0)+1 as id from Pessoa";
		
		try {
			pr = conn.prepareStatement(sql);
			st = pr.executeQuery();
			if(st.next()) {
				maxId = st.getLong("id");				
			}
			
		} finally {
			pr.close();
			st.close();
		}
		return maxId;		
	}

	public void delete(Long id) throws SQLException {
		PreparedStatement st = null;
		String sql = "delete from Pessoa where id = ?";
		
		try {
			st = conn.prepareStatement(sql);
			st.setLong(1, id);
			st.executeUpdate();
		} finally {
			st.close();
		}
		
	}

	public List<Pessoa> listaCadastro() throws SQLException {
		PreparedStatement pr = null;
		ResultSet st = null;
		String sql = "select id, nome, altura from Pessoa";
		List<Pessoa> all = new ArrayList<>();
		
		try {
			pr = conn.prepareStatement(sql);
			st = pr.executeQuery();
			
			while(st.next()) {
				Pessoa pessoa = new Pessoa(st.getLong("id"),
						                   st.getString("nome"),
						                   st.getDouble("altura"));
				all.add(pessoa);
			}
		} finally {
			pr.close();
			st.close();			            
		}
		return all;
	}

}
