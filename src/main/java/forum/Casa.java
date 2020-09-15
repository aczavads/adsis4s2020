package caio2020_09_15.forum;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

public class Casa {
	private String nome;
	private Dono dono;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "casa_nome")
	private List<Porta> portas = new ArrayList<>();
	private List<Janela> janelas = new ArrayList<>();

	public Casa() {

	}

	public Casa(String nome) {
		this();
		this.nome = nome;
	}

	public void setDono(Dono dono) {
		this.dono = dono;
	}

	public Dono getDono() {
		return dono;
	}

	public void addJanela(Janela novo) {
		this.janelas.add(novo);
	}

	public String getNome() {
		return nome;
	}

	public void addPorta(Porta novo) {
		this.portas.add(novo);
	}

	

	

}