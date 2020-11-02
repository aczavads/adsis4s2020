package aula20201027.pessoa;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Fisica extends Pessoa {
    private Date dataDeNascimento;
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

}
