package aepSegundoBimestre;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Imovel {
    @Id
    private String id;
    private String descricao;
    private double metragem;
    private double precoCompra;
    private int anoAdquisicao;
    private String endereco;
    @Enumerated(EnumType.STRING)
    private TipoImovel tipoImovel;

    public Imovel() {
        id = UUID.randomUUID().toString();
    }

    public Imovel(String descricao, double metragem,double precoCompra, int anoAdquisicao, TipoImovel tipoImovel,String endereco) {
        this();
        this.descricao = descricao;
        this.metragem = metragem;
        this.precoCompra = precoCompra;
        this.anoAdquisicao = anoAdquisicao;
        this.tipoImovel = tipoImovel;
        this.endereco = endereco;
    }

    public Imovel(String id,String descricao, double metragem,double precoCompra, int anoAdquisicao, TipoImovel tipoImovel,String endereco) {
        this(descricao, metragem,precoCompra, anoAdquisicao, tipoImovel,endereco);
        this.id = id;
	}
    

    public String getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getMetragem() {
		return metragem;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public int getAnoAdquisicao() {
		return anoAdquisicao;
	}

	public String getEndereco() {
		return endereco;
	}

	public TipoImovel getTipoImovel() {
		return tipoImovel;
	}

	@Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Imovel)) {
            return false;
        }
        Imovel imovel = (Imovel) o;
        return Objects.equals(id, imovel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    
}
