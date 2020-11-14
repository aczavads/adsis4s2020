package aula20200804.transações;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class AppTransações extends JDialog {
	private static final long serialVersionUID = 1L;
	private JButton btnExecutar = new JButton("Executar");
	private JButton btnDesfazer = new JButton("Rollback");
	private JButton btnEfetivar = new JButton("Commit");
	private Connection conexão = null;
	

	public void testarCloseDoResultSet2()  {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexão.prepareStatement("select 1+1 as soma");
			ps.setString(1, "x");
			rs = ps.executeQuery();
			//use o rs aqui...
		} catch (Exception e) {
			//tratar a exceção aqui.
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void testarCloseDoResultSet() throws Exception {
		try (PreparedStatement ps = conexão.prepareStatement("select 1+1 as soma")) {
			ps.setString(1, "x");
			try (ResultSet rs = ps.executeQuery()) {
				//use o rs aqui...
			}
		}
	}

	public AppTransações() {
		this.setLayout(new FlowLayout());
		btnExecutar.addActionListener( new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Clicou no executar!");
				long inicio = System.currentTimeMillis();
				executar();
				JOptionPane.showMessageDialog(null, "Executar concluído! Tempo total=" + (System.currentTimeMillis()-inicio));
			}
		});
		btnDesfazer.addActionListener((e) -> {
			JOptionPane.showMessageDialog(null, "Clicou no desfazer!");
			desfazer();
		});
		btnEfetivar.addActionListener((e) -> {
			JOptionPane.showMessageDialog(null, "Clicou no efetivar!");
			efetivar();
		});
		this.add(btnExecutar);
		this.add(btnDesfazer);
		this.add(btnEfetivar);
		this.abrirConexão();
	}
	
	private void efetivar() {
		try {
			conexão.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void desfazer() {
		try {
			conexão.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void executar() {
		PreparedStatement inserirMensagem = null;
		try {
			inserirMensagem = conexão.prepareStatement("insert into teste (mensagem) values (?)");
			
			for (int i = 0; i < 2000; i++) {
				inserirMensagem.setString(1, "Mensagem nro " + i + " >> " + new Date().toLocaleString());
				inserirMensagem.executeUpdate();
				if (i == 1200) {
					throw new Exception("Erro no 1200!");
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inserirMensagem != null) {
				try {
					inserirMensagem.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}

	private void abrirConexão() {
		try {
			conexão = DriverManager.getConnection("jdbc:postgresql://localhost:5432/adsis4s2020","postgres","postgres");
			conexão.setAutoCommit(false);
			
			conexão.createStatement().execute("create table if not exists teste (mensagem varchar(255) not null)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		AppTransações tela = new AppTransações();
		tela.setSize(300, 150);
		tela.setDefaultCloseOperation(tela.DISPOSE_ON_CLOSE);
		tela.setVisible(true);
	}

}
