package aula20200821.repositoryCompleto;

public class Imóvel {
	private Long id;
	private String descrição;
	private Double metragem;

	public Imóvel(Long id, String descrição, Double metragem) {
		this(descrição, metragem);
		this.id = id;		
	}

	public Imóvel(String descrição, Double metragem) {
		this.descrição = descrição;
		this.metragem = metragem;
	}
	
	public String getDescrição() {
		return descrição;
	}
	public Double getMetragem() {
		return metragem;
	}
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Imóvel [id=" + id + ", descrição=" + descrição + ", metragem=" + metragem + "]";
	}
	
	
	

}
