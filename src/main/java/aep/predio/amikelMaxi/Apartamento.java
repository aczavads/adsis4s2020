package aep.predio.amikelMaxi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Apartamento {
	@Id
	private String numero;
	private String descripcao;
	
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "garagApto")
	private List<Garagem> garagensApartamento = new ArrayList<>();
	
	public Apartamento() {
		this.numero = UUID.randomUUID().toString();
	}
	
	public Apartamento(String descripcao) {
		this();
		this.descripcao = descripcao;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDescripcao() {
		return descripcao;
	}

	public void setDescripcao(String descripcao) {
		this.descripcao = descripcao;
	}
	
	public void setGaragensApartamento(Garagem garagensApartamento) {
		this.garagensApartamento.add(garagensApartamento);
	}
	public List<Garagem> getGaragensApartamento() {
		return garagensApartamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apartamento other = (Apartamento) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	
	

}