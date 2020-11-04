package aula20201027.pedido;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aula20201027.produto.IdsDiveregentesException;
import aula20201027.produto.RegistroDuplicadoException;
import aula20201027.produto.RegistroNãoEncontradoException;

@RestController
@RequestMapping("/api/pedidos")
@Transactional(value = TxType.REQUIRED)
public class PedidoController {
    @Autowired
    private PedidoRepository repo;

    @GetMapping
    public List<Pedido> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")    
    public Pedido getById(@PathVariable String id) {
        return repo.findById(id).get();
    }

    @PostMapping
    public String post(@RequestBody Pedido novo) {
        if (repo.findById(novo.getId()).isPresent()) {            
            throw new RegistroDuplicadoException();
        }
        for (ItemPedido it : novo.getItens()) {
            it.setPedido(novo);
        }
        novo = repo.save(novo);
        return novo.getId();
    }

    @DeleteMapping("/{id}")    
    public void deleteById(@PathVariable String id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public void put(@PathVariable String id, @RequestBody Pedido atualizado) {
        if (!id.equals(atualizado.getId())) {
            throw new IdsDiveregentesException();
        } 
        if (!repo.findById(atualizado.getId()).isPresent()) {            
            throw new RegistroNãoEncontradoException();

        }
        for (ItemPedido it : atualizado.getItens()) {
            it.setPedido(atualizado);
        }
        repo.save(atualizado);
    }    
    
}
