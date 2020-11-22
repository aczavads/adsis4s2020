package aula20201120.baseController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import aula20201027.produto.IdsDiveregentesException;


public class BaseController<ENTITY extends BaseEntity, REPOSITORY extends JpaRepository<ENTITY, String>> {
    @Autowired
    private REPOSITORY repo;
    

    @GetMapping
    public List<ENTITY> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ENTITY getById(@PathVariable("id") String id) {
        return repo.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        repo.deleteById(id);
    }

    @PostMapping
    public String post(@RequestBody ENTITY nova) {
        repo.save(nova);
        return nova.getId();
    }

    @PutMapping("/{id}")
    public void put(@PathVariable("id") String id, @RequestBody ENTITY atualizada) {
        if (!atualizada.getId().equals(id)) {
            throw new IdsDiveregentesException();
        }
        repo.save(atualizada);
    }
    
}
