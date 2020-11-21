package aula20201120.baseController.receita;

import javax.persistence.Entity;

import aula20201120.baseController.BaseEntity;

@Entity
public class Receita extends BaseEntity {
    private String nome;

    public Receita() {
        super();
    }

    public String getNome() {
        return nome;
    }
}
