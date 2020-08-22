package hector_victor.aula20200821;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AppRepositorioPessoa {
	public static void main(String[] args) throws SQLException {
		ConnectionManager conn = new ConnectionManager();
		
		try {
			PessoaRepository ts = new PessoaRepository(conn);
			Pessoa pessoa1 = new Pessoa("hector",1.80);
			pessoa1 = ts.salvar(pessoa1);
			conn.commit();
			
			Long id = pessoa1.getId();
			System.out.println("Procurando por Id: "+id);
			
			Pessoa encontrarId = ts.findById(id); 
			System.out.println(encontrarId.toString());
			
			 ts.delete(id);
			if(ts.findById(id) == null) {
				System.out.println("Excluido com Sucesso!!");				
			}
			System.out.println(ts.findById(id));
			
			List<Pessoa> cadastroPessoa = ts.listaCadastro();
			
			System.out.println("-----------------------------");
			System.out.println("LISTA DE PESSOAS");
			for (Pessoa pessoa : cadastroPessoa) {
				System.out.println(pessoa);
			}
			
			conn.close();
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
