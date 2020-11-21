package aula20201120.baseController.ingrediente;

import javax.persistence.Entity;

import aula20201120.baseController.BaseEntity;


@Entity
public class Ingrediente extends BaseEntity {
    private String nome;

    public Ingrediente() {
        super();
    }

    public String getNome() {
        return nome;
    }
}
