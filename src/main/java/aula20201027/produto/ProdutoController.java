package aula20201027.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repo;

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



    
}
