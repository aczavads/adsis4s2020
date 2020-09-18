package caioAdsis4s2020;

public class Imovel {
	private Long id;
	private String descrição;
	private Double metragem;

	public Imovel(Long id, String descrição, Double metragem) {
		this(descrição, metragem);
		this.id = id;		
	}

	public Imovel(String descrição, Double metragem) {
		this.descrição = descrição;
		this.metragem = metragem;
	}
	
	public String getDescricao() {
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
