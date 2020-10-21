package aula20200918.venda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido extends BaseEntity {
    private Long numero;

    @Temporal(TemporalType.DATE)
    private Date emitidoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pessoa cliente;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido",fetch = FetchType.LAZY)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido() {
        super();
    }

    public Pedido(Long numero, Date emitidoEm, Pessoa cliente) {
        this();
        this.numero = numero;
        this.emitidoEm = emitidoEm;
        this.cliente = cliente;
    }

    public Long getNumero() {
        return numero;
    }

    public Date getEmitidoEm() {
        return emitidoEm;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public BigDecimal getValorTotal() {
        System.out.println(":::::::>>> tipo da coleção Pedido.itens: " + itens.getClass().getName());
        BigDecimal valorTotal = new BigDecimal("0.00");
        for (ItemPedido itemPedido : itens) {
            valorTotal = valorTotal.add(itemPedido.getValorTotal());
        }
        return valorTotal;
    }

	public void adicionarItem(Produto produto, BigDecimal quantidade, BigDecimal descontoPercentual) {
        ItemPedido novo = new ItemPedido(this, produto, quantidade, descontoPercentual);
        this.itens.add(novo);
	}

}
