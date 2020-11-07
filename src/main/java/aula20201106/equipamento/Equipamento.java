package aula20201106.equipamento;

import javax.persistence.Entity;

import aula20201027.BaseEntity;

@Entity
public class Equipamento extends BaseEntity {
    private String descricao;

    public Equipamento() {
        super();
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
