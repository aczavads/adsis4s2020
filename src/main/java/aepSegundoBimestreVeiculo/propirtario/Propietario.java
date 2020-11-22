package aepSegundoBimestreVeiculo.propirtario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import aepSegundoBimestreVeiculo.Veiculo;
import aula20201020.rest.CameraFotografica;

@Entity
public class Propietario {

	@Id
	private String id;
	private String cpf;
	private String nome;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "propietario",fetch = FetchType.LAZY)
	private List<Veiculo> veiculos = new ArrayList<>();
	
	public Propietario() {
		super();
	}
	
	public Propietario(String cpf, String nome) {
		this();
		this.cpf =  cpf;
		this.nome = nome;
		this.veiculos = veiculos;
	}

	public String getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void adicionarVeiculos(Veiculo ve) {
		this.veiculos.add(ve);
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (o == this)
	            return true;
	        if (!(o instanceof Propietario)) {
	            return false;
	        }
	        Propietario propietario = (Propietario) o;
	        return Objects.equals(id, propietario.id);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id);
	    }

	
}
