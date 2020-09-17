package aula20200915.bidirecionais;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pais {
    @Id
    private String id;
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
    //@JoinColumn(name = "pais_id")
    private List<Estado> estados = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "distrito_federal_id", table = "pais")
    private Estado distritoFederal;

    public Pais() {
        this.id = UUID.randomUUID().toString();
    }

    public Pais(String nome) {
        this();
        this.nome = nome;
    }

    public void setDistritoFederal(Estado distritoFederal) {
        this.distritoFederal = distritoFederal;
    }

    public Estado getDistritoFederal() {
        return distritoFederal;
    }

    public void addEstado(Estado novo) {
        this.estados.add(novo);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            "}";
    }


}
