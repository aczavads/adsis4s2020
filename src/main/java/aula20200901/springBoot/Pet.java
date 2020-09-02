package aula20200901.springBoot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	public Pet() {
	}

	public Pet(String nome) {
		super();
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", nome=" + nome + "]";
	}
	

}
