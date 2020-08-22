package caioAdsis4s2020_08_21;

public class Carro {
	private Long id;
	private String modelo;
	private Double kilometragem;

	public Carro(Long id, String modelo, Double kilometragem) {
		this(modelo, kilometragem);
		this.id = id;

	}

	public Carro(String modelo, Double kilometragem) {
		this.modelo = modelo;
		this.kilometragem = kilometragem;
	}
	public String getModelo() {
		return modelo;
	}
	public Double getKilometragem() {
		return kilometragem;
	}
	public Long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Imóvel [id=" + id + ", modelo=" + modelo + ", kilometragem=" + kilometragem + "]";
	}	
}
