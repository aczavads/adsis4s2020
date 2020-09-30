package exemploDeAssocoaciaoDeClasse;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Carro {
	
	@Id
	private String id;
	private String descricao;
	private String placa;
	private int anoFabricacao;

	public Carro() {
		this.id = UUID.randomUUID().toString();
	}
	public Carro(String descricao, String placa, int anoFabricacao) {
		this();
		this.anoFabricacao = anoFabricacao;
		this.descricao = descricao;
		this.placa = placa;
	}
	public int getAnoFabricacao() {
		return anoFabricacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getId() {
		return id;
	}
	public String getPlaca() {
		return placa;
	}

}
