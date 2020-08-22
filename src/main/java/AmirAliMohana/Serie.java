package AmirAliMohana;


public class Serie {
	private Long id;
	private String descrição;
	private Double avaliação;

	public Serie(Long id, String descrição, Double avaliação) {
		this(descrição, avaliação);
		this.id = id;		
	}

	public Serie(String descrição, Double avaliação) {
		this.descrição = descrição;
		this.avaliação = avaliação;
	}
	
	public String getDescrição() {
		return descrição;
	}
	public Double getAvaliação() {
		return avaliação;
	}
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Imóvel [id=" + id + ", descrição=" + descrição + ", Avaliação=" + avaliação + "]";
	}
	
	
	

}
