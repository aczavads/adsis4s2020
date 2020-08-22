package adsis4s2020.Maxi.fork.produto;
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
public class ProdutoRepository {
	private ConnectionManager connectionManager;
	public ProdutoRepository(ConnectionManager connectionManager) throws SQLException {
		this.connectionManager = connectionManager;
		this.ciarTabela();
	}
	private void ciarTabela()  {
		PreparedStatement pst = null;
		try {
			pst = connectionManager.preparedStatement("create table if not exists tb_produto ("
					+ "codigo long not null primary key,"
					+ "descricao varchar(255) not null,"
					+ "preco number(9,2) not null"
					+ ")");
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(pst != null) {
			try {
				pst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public Produto buscarPorCodigo(Long codigo) throws SQLException {
		Produto produtoEncontrado = null;
		PreparedStatement selectPorCodigo = connectionManager.preparedStatement("select * from tb_produto where codigo = ?");
		selectPorCodigo.setLong(1, codigo);
		//selectPorCodigo.execute();
		ResultSet rst = selectPorCodigo.executeQuery();
		try {
			if(rst.next()) {
				rst.getLong("codigo");
				rst.getString("descricao");
				rst.getDouble("preco");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			selectPorCodigo.close();
			rst.close();
		}
		return produtoEncontrado;
	}
	
	public Produto guardar(Produto produto) {
		boolean existe = produto.getCodigo() != null;
		PreparedStatement pstS = null;
		try {
			if(existe == true) {
				pstS = connectionManager.preparedStatement( "update tb_produto set descricao = ? ,preco = ? where codigo = ?");
				pstS.setString(1, produto.getDescricao());
				pstS.setDouble(2, produto.getPreco());
				pstS.setLong(3, produto.getCodigo());
			}else {
				Long novoCodigo = buscarNovoCodigo();
				System.out.println("Novo código: "+ novoCodigo);
				pstS = connectionManager.preparedStatement( "insert into tb_produto values(?,?,?)");
				pstS.setLong(1, novoCodigo);
				pstS.setString(2, produto.getDescricao());
				pstS.setDouble(3, produto.getPreco());
				produto = new Produto(novoCodigo,produto.getDescricao(), produto.getPreco());
			}
			pstS.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				pstS.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return produto;
	}
	private Long buscarNovoCodigo() throws SQLException {
		PreparedStatement stCod = connectionManager.preparedStatement("select coalesce(max(codigo),0)+1 as ultimo from tb_produto");
		ResultSet rs = stCod.executeQuery();
		try {
			if(rs.next()) {
				return rs.getLong("ultimo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			stCod.close();
			rs.close();
		}
		return null;
	}
	public void detelePorCodigo(Long cod) throws SQLException {
		PreparedStatement pstDelete = connectionManager.preparedStatement("delete from tb_produto where codigo = ?");
		try {
			pstDelete.setLong(1, cod);
			pstDelete.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pstDelete.close();
		}
		
	}
	
	public List<Produto> findAll() throws SQLException{
		List<Produto> all = new ArrayList<>();
		PreparedStatement pstSelect = connectionManager.preparedStatement("select * from td_produto");
		ResultSet st = pstSelect.executeQuery();
		try {
			while(st.next()){
				Produto produto = new Produto(st.getLong("codigo"),st.getString("descricao"),st.getDouble("preco"));
				all.add(produto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pstSelect.close();
			st.close();
		}
		
		
		return all;
	}

}
