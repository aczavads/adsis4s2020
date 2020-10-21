package aep.predio.amikelMaxi;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Endereco {
	
	@Id 
	private String codigo;
	private String logaduro;
	private String numero;
	private String bairro;
	
	public Endereco() {
		this.codigo = UUID.randomUUID().toString();
	}
	public Endereco(String logaduro, String numero, String bairro) {
		this();
		this.bairro = bairro;
		this.logaduro = logaduro;
		this.numero = numero;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getLogaduro() {
		return logaduro;
	}
	public void setLogaduro(String logaduro) {
		this.logaduro = logaduro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	
	

}