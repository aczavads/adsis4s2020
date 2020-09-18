package caio2020_09_15.forum;

import javax.persistence.Entity;

@Entity
public class Porta {
	private String nome;

	public Porta() {
	}

	public Porta(String nome) {
		this();
		this.nome = nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}