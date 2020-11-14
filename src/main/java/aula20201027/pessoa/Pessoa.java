package aula20201027.pessoa;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import aula20201027.BaseEntity;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa extends BaseEntity {
    private String nome;

    public String getNome() {
        return nome;
    }
    
}
