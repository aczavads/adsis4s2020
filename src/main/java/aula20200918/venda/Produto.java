package aula20200918.venda;

import java.math.BigDecimal;

import javax.persistence.Entity;

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

}
