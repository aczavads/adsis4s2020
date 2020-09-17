package aula20200825.abstractRepository;

public class Livro {
	private Long id;
	private String título;
	private String autor;
	private int quantidadeDePáginas;

	public Livro(String título, String autor, int quantidadeDePáginas) {
		this.título = título;
		this.autor = autor;
		this.quantidadeDePáginas = quantidadeDePáginas;
	}
	
	public Livro(Long id, String título, String autor, int quantidadeDePáginas) {
		this(título, autor, quantidadeDePáginas);
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	
	public void setTítulo(String título) {
		this.título = título;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public void setQuantidadeDePáginas(int quantidadeDePáginas) {
		this.quantidadeDePáginas = quantidadeDePáginas;
	}


	public String getTítulo() {
		return título;
	}

	public String getAutor() {
		return autor;
	}

	public int getQuantidadeDePáginas() {
		return quantidadeDePáginas;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", título=" + título + ", autor=" + autor + ", quantidadeDePáginas="
				+ quantidadeDePáginas + "]";
	}

	
	

}
