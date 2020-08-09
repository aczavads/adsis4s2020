package adsis4sem2020;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AppTarefa {
	public static void main(String[] args) {
		PreparedStatement tabela = null;
		try {
			
	   Connection conexão = DriverManager.getConnection("jdbc:h2:~/teste2","id","numero");
	   tabela = conexão.prepareStatement("insert into teste (mensagem) values (?)");
	   for (int i = 0; i < 2000; i++) {
			tabela.setString(i,"foi");
	   }
		}catch (Exception e) {
	 e.printStackTrace();
	}
}
}
