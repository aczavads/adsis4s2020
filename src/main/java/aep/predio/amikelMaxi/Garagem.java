package aep.predio.amikelMaxi;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Garagem {

	@Id
	private String codigo;
	private String localizacao;
	private String numero;
	
	public Garagem() {
		this.codigo = UUID.randomUUID().toString();
	}
	
	public Garagem(String numero,String localizacao) {
		this();
		this.localizacao = localizacao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
}
