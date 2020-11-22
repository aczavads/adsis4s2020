package aepSegundoBimestreVeiculo;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import aula20201016.rest.CameraFotografica;

@Entity
public class Veiculo {
	@Id
	private String id;
	private String placa;
	private String modelo;
	private String marca;
	@Enumerated(EnumType.STRING)
	private CorVeiculo cor;
	private int anoFabricacao;
	private double preco;
	
	public Veiculo() {
		id = UUID.randomUUID().toString();
	}
	
	public Veiculo(String placa, String modelo, String marca,CorVeiculo cor, int anoFabricacao, double preco) {
		this();
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.cor = cor;
		this.anoFabricacao = anoFabricacao;
		this.preco = preco;
	}
	
	public Veiculo(String id,String placa, String modelo, String marca,CorVeiculo cor, int anoFabricacao, double preco) {
		this(placa,modelo,marca,cor,anoFabricacao,preco);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getPlaca() {
		return placa;
	}

	public String getModelo() {
		return modelo;
	}

	public String getMarca() {
		return marca;
	}
	public void setCor(CorVeiculo cor) {
		this.cor = cor;
	}
	public CorVeiculo getCor() {
		return cor;
	}

	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public double getPreco() {
		return preco;
	}
	public double getTotalPrecoDeVeiculos() {
		double total = 0.00;
		return 0;
		
	}

	@Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Veiculo)) {
            return false;
        }
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(id, veiculo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
	
}
