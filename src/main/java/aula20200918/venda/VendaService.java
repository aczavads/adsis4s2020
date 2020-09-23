package aula20200918.venda;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VendaService {
        @Autowired
        private EntityManager em;

        public void incluirDadosIniciais() {
                em.createQuery("delete from ItemPedido").executeUpdate();
                em.createQuery("delete from Pedido").executeUpdate();
                em.createQuery("delete from Pessoa").executeUpdate();
                em.createQuery("delete from Produto").executeUpdate();

                Pessoa maria = new Pessoa("Maria de Fátima");

                Produto fraldaPampersRN = new Produto("Fralda Pampers ConfortSec RN c/ 56", new BigDecimal("68.90"));
                Produto acémMoído = new Produto("Acém moído", new BigDecimal("23.15"));

                Pedido p01Maria = new Pedido(1L, new Date(), maria);

                p01Maria.adicionarItem(fraldaPampersRN, new BigDecimal("2.00"), new BigDecimal("10.00"));
                p01Maria.adicionarItem(acémMoído, new BigDecimal("2.50"), new BigDecimal("10.00"));

                // ItemPedido ip1 = new ItemPedido(p01Maria, fraldaPampersRN, new
                // BigDecimal("2.00"), new BigDecimal("10.00"));

                em.persist(maria);
                em.persist(fraldaPampersRN);
                em.persist(acémMoído);
                em.persist(p01Maria);
                em.flush();

        }

        public Pedido recuperarPedidoPeloNúmero(long númeroDoPedido) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                TypedQuery<Pedido> query = em.createQuery("select p from Pedido p where p.numero = :numero",
                                Pedido.class);
                Pedido recuperado = query.setParameter("numero", númeroDoPedido).getSingleResult();
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                return recuperado;
        }

}
