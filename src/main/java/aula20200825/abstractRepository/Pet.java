package aula20200825.abstractRepository;

public class Pet extends AbstractEntity {
	private String nome;
	
	public Pet(Long id, String nome) {
		super(id);
		this.nome = nome;
	}
	
	public Pet(String nome) {
		super();
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "Pet [id=" + getId() + ", nome=" + nome + "]";
	}
	
	
	
	

}
