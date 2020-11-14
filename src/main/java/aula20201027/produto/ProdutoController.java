package aula20201027.produto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repo;


    @GetMapping("/média-do-preco-atual")
    public BigDecimal getMédiaDoPreçoAtual() {
        BigDecimal valor = new BigDecimal("0.00");
        //return repo.obterMédiaDoPreçoAtualDosProdutos();
        List<BigDecimal> preçosAtuais = repo.obterPreçosAtuaisDosProdutos();
        for (BigDecimal preçoAtual : preçosAtuais) {
            valor = valor.add(preçoAtual);
        }
        valor = valor.divide(
            new BigDecimal(preçosAtuais.size()), 
            RoundingMode.HALF_UP).setScale(2); 
        return valor;
    }

    @GetMapping
    public List<Produto> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")    
    public Produto getById(@PathVariable String id) {
        return repo.findById(id).get();
    }

    @PostMapping
    public String post(@RequestBody Produto novo) {
        if (repo.findById(novo.getId()).isPresent()) {            
            throw new RegistroDuplicadoException();
        }
        novo = repo.save(novo);
        return novo.getId();
    }

    @DeleteMapping("/{id}")    
    public void deleteById(@PathVariable String id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public void put(@PathVariable String id, @RequestBody Produto atualizado) {
        if (!id.equals(atualizado.getId())) {
            throw new IdsDiveregentesException();
        }
        if (!repo.findById(atualizado.getId()).isPresent()) {            
            throw new RegistroNãoEncontradoException();

        }
        repo.save(atualizado);
    }
    
}
    