package caio2020_09_15.forum;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;




public class Dono {

	private Number cpf;
	private String nome;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Dono_cpf")
	private List<Casa> casas = new ArrayList<>();


	public Dono(String nome,double cpf) {
			this.cpf= cpf;
	        this.nome = nome;
	    }
	

	public void addCasa(Casa novo) {
		this.casas.add(novo);
	}

	public String getNome() {
		return nome;
	}
	
	public Number getCpf() {
		return cpf;
	}

	public List<Casa> getCasas() {
		return casas;
	}

}
