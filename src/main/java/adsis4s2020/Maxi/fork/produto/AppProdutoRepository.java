package adsis4s2020.Maxi.fork.produto;
import java.io.*;
import java.sql.*;
import java.util.Scanner;
public class AppProdutoRepository
{

	public static void main(String[] args)throws SQLException {
		ConnectionManager conM = new ConnectionManager();
		try {
			ProdutoRepository proR = new ProdutoRepository(conM);
			Produto cafe = new Produto("Café jandaia", 10.87);
			cafe = proR.guardar(cafe);
			conM.commit();
			
			Long cod = cafe.getCodigo();
			System.out.println("Procurando por código: "+cod);
			
			proR.detelePorCodigo(cod);
			System.out.println("Apos de apagar o produto, o código é: " + proR.buscarPorCodigo(cod));
			
			
			} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			conM.close();
		}
		
	}	
	}
