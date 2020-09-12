package aula20200911.simplificando;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Estado {
    @Id
    private String id;
    private String nome;

    public Estado() {
        this.id = UUID.randomUUID().toString();
    }

	public Estado(String nome) {
        this();
        this.nome = nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public String getId() {
        return id;
    }

}
