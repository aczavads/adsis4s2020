package aula20201027.pessoa;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Juridica extends Pessoa {
    private String cnpj;
    private String inscricaoEstadual;
    private BigDecimal valorDoCapitalSocial;

    public String getCnpj() {
        return cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public BigDecimal getValorDoCapitalSocial() {
        return valorDoCapitalSocial;
    }

}
