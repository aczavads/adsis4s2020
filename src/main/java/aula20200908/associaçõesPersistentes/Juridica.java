package aula20200908.associaçõesPersistentes;

import javax.persistence.Entity;

@Entity
public class Juridica extends Pessoa {	
	private String nomeFantasia;
	private String cnpj;
	private String inscricaoEstadual;

	public Juridica() {
	}
	
	public Juridica(String nome, String nomeFantasia, String cnpj, String inscricaoEstadual) {
		super(nome);
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	

}
