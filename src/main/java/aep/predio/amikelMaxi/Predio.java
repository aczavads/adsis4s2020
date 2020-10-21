package aep.predio.amikelMaxi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Predio {
	
	@Id
	private String codigo;
	private String descripcao;
	private String estado;
	private int ano;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "apartementPredio")
	private List<Apartamento> apartamentos = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "enderecoPredio", table ="predio")
	private Endereco enderecoApartamento;
	
	public Predio() {
		this.codigo = UUID.randomUUID().toString();
	}
	
	public Predio(String descripcao, String estado, int ano) {
		this();
		this.ano = ano;
		this.descripcao = descripcao;
		this.estado = estado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcao() {
		return descripcao;
	}

	public void setDescripcao(String descripcao) {
		this.descripcao = descripcao;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public void setApartamento(Apartamento apt) {
		this.apartamentos.add(apt);
	}
	
	public List<Apartamento> getApartamentos(){
		return this.apartamentos;
	}
	
	public void setEndereco(Endereco ender) {
		this.enderecoApartamento = ender;
	}
	public Endereco getEnderecoApartamento() {
		return enderecoApartamento;
	}
	
	
	
	
	

}