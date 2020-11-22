package aepSegundoBimestre;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/imoveis")
public class ImovelController {
    @Autowired
    private ImovelRepository repo;

    @GetMapping
    public List<Imovel> getAll() {
        return repo.findAll();
    }

    @PostMapping 
    public ResponseEntity<String> post(@RequestBody Imovel nova) {
        repo.save(nova); 
        return ResponseEntity.status(HttpStatus.CREATED).body(nova.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
    	try {
    		repo.deleteById(id);
    		return ResponseEntity.ok().build();
		} catch (Exception e) {
    		return ResponseEntity.notFound().build();
		}
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imovel> getById(@PathVariable("id") String id) {
    	try {
            //return ResponseEntity.ok(repo.findById(id));
    		return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id).get());
		} catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
		}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable("id") String id, @RequestBody Imovel imovel) {
        if (!id.equals(imovel.getId())) {
            //throw new ChaveDeRegistroInexistente();
        }
        repo.save(imovel);
        return ResponseEntity.ok().build();
    }


    
}
