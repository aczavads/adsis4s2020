package aula20200825.tryWithResources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AppTryWithResources {

	public static void main(String[] args) {
		//exemploTípicoComTryCatchNormal();

		//exemploComTryWithResources();

		
		//try with resources que aceita qualquer classe que implemente AutoCloseable, e que garante
		//que o método close() será invocado em um finally implícito.
		try (Cofre c = new Cofre()) { 
			c.open();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		/*
		//try/catch/finally normal
		Cofre c = null;
		try {
			c = new Cofre();
			c.open();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				c.close();
			}
		}
		*/
		
		

	}

	private static void exemploComTryWithResources() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("");
			rs = ps.executeQuery();
			// Seu algoritmo vai aqui...
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

	private static void exemploTípicoComTryCatchNormal() {
		try (
				Connection conn = null; 
				PreparedStatement ps = conn.prepareStatement(""); 
				ResultSet rs = ps.executeQuery(); 
			) {
			// Seu algoritmo vai aqui...
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
