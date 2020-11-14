package aula20201027.produto;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, String> {

    @Query(value = "select round(avg(preco_atual),2) from produto", nativeQuery = true)
    BigDecimal obterMédiaDoPreçoAtualDosProdutos();


    @Query(value = "select preco_atual from produto", nativeQuery = true)
    List<BigDecimal> obterPreçosAtuaisDosProdutos();

}
