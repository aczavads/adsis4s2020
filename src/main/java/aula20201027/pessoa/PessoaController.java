package aula20201027.pessoa;

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

import aula20201027.produto.IdsDiveregentesException;
import aula20201027.produto.RegistroDuplicadoException;
import aula20201027.produto.RegistroNãoEncontradoException;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    @Autowired
    private PessoaRepository repo;

    @GetMapping
    public List<Pessoa> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Pessoa getById(@PathVariable String id) {
        return repo.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        repo.deleteById(id);
    }

    private String executePost(Pessoa nova) {
        if (repo.findById(nova.getId()).isPresent()) {
            throw new RegistroDuplicadoException();
        }
        nova = repo.save(nova);
        return nova.getId();
    }

    private void executePut(String id, Pessoa atualizada) {
        if (!id.equals(atualizada.getId())) {
            throw new IdsDiveregentesException();
        }
        if (!repo.findById(atualizada.getId()).isPresent()) {
            throw new RegistroNãoEncontradoException();

        }
        repo.save(atualizada);
    }

    @GetMapping("/fisicas")
    public List<Fisica> getAllFisicas() {
        return repo.findAllFisica();
    }

    @PostMapping("/fisicas")
    public String postFisicas(@RequestBody Fisica nova) {
        return executePost(nova);
    }

    @PutMapping("/fisicas/{id}")
    public void putFisicas(@PathVariable String id, @RequestBody Fisica atualizada) {
        executePut(id, atualizada);
    }

    @GetMapping("/juridicas")
    public List<Juridica> getAllJuridicas() {
        return repo.findAllJuridica();
    }

    @PostMapping("/juridicas")
    public String postJuridicas(@RequestBody Juridica nova) {
        return executePost(nova);
    }

    @PutMapping("/juridicas/{id}")
    public void putJuridicas(@PathVariable String id, @RequestBody Juridica atualizada) {
        executePut(id, atualizada);
    }

}
