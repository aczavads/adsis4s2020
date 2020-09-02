package aula20200901.springBoot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	private String autor;
	private Integer quantidadeDePaginas;

		
	public Livro(String titulo, String autor, Integer quantidadeDePaginas) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.quantidadeDePaginas = quantidadeDePaginas;
	}

	public Livro() {
	}
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getAutor() {
		return autor;
	}
	public Integer getQuantidadeDePaginas() {
		return quantidadeDePaginas;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", quantidadeDePaginas="
				+ quantidadeDePaginas + "]";
	}
	
	
	

}
