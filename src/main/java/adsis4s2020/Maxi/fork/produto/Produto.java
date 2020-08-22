package adsis4s2020.Maxi.fork.produto;

public class Produto {
	private Long codigo;
	private String descricao;
	private double preco;
	public Produto(Long codigo, String descricao, double preco) {
		this(descricao,preco);
		this.codigo = codigo;
	}
	public Produto(String descricao, double preco) {
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public double getPreco() {
		return preco;
	}
	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + "]";
	}
	
	

}
