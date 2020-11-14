package aula20201027.produto;

import java.math.BigDecimal;

import javax.persistence.Entity;

import aula20201027.BaseEntity;

@Entity
public class Produto extends BaseEntity {
    private String nome;
    private BigDecimal precoAtual;

    public Produto() {
        super();
    }

    public Produto(String nome, BigDecimal precoAtual) {
        this();
        this.nome = nome;
        this.precoAtual = precoAtual;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPrecoAtual() {
        return precoAtual;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrecoAtual(BigDecimal precoAtual) {
        this.precoAtual = precoAtual;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            " nome='" + getNome() + "'" +
            ", precoAtual='" + getPrecoAtual() + "'" +
            "}";
    }


}
