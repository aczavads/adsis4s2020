package exemploDeAssocoaciaoDeClasse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pessoa {
	@Id
	private String id;
	private String nome;
	private String cpf;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Carro> carros = new ArrayList<>();

	public Pessoa() {
		this.id = UUID.randomUUID().toString();
	}
	public Pessoa(String nome, String cpf) {
		this();
		this.cpf = cpf;
		this.nome = nome;
	}

	public void addCarro(Carro car) {
		this.carros.add(car);	
	}
	public List<Carro> getCarros() {
		return carros;
	}
	public String getCpf() {
		return cpf;
	}
	public String getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}

}
