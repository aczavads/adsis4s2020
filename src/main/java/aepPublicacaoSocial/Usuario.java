package aepPublicacaoSocial;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	@Id
	private String id;
	private String nome;
	private int dataNascimento;
	@OneToMany
	private List< Postagem > postagens = new ArrayList<>();
	
	public Usuario () {
		id = UUID.randomUUID().toString();
	}
	
	public Usuario (String nome, int anoNsacimento) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.postagens = postagens;
		
	}
	
	public Usuario (String id,String nome, int anoNsacimento) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.postagens = postagens;
		
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getDataNascimento() {
		return dataNascimento;
	}

	public List<Postagem> getPostagens() {
		return postagens;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataNascimento(int dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setPostagens(Postagem posts) {
		this.postagens.add(posts);
	}
	
	public int getTotalPostagens() {
		int total =0;
		for (Postagem postagem : postagens) {
			total = total + 1;
		}
		return total;
	}
	
	

}
