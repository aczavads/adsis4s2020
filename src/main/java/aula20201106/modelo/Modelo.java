package aula20201106.modelo;

import javax.persistence.Entity;

import aula20201027.BaseEntity;

@Entity
public class Modelo extends BaseEntity {
    private String descricao;

    public Modelo() {
        super();
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
