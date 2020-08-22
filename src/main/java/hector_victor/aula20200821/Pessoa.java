package hector_victor.aula20200821;

public class Pessoa {

	private Long id;
	private String nome;
	private Double altura;

	public Pessoa(Long id, String nome, Double altura) {
		this.id = id;
		this.nome = nome;
		this.altura = altura;
	}

	public Pessoa(String nome, Double altura) {
		this.nome = nome;
		this.altura = altura;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Double getAltura() {
		return altura;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", altura=" + altura + "]";
	}

}
