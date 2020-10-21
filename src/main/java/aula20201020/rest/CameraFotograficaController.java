package aula20201020.rest;

import java.util.List;
import java.util.Objects;

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
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/api/camerasfotograficas")
public class CameraFotograficaController {
    @Autowired
    private CameraFotograficaRepositoryInMemory repo;

    @GetMapping
    public List<CameraFotografica> getAll() {
        return repo.findAll();
    }

    @PostMapping 
    public ResponseEntity<String> post(@RequestBody CameraFotografica nova) {
        repo.save(nova); 
        return ResponseEntity.status(HttpStatus.CREATED).body(nova.getId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        repo.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CameraFotografica> getById(@PathVariable("id") String id) {
    	try {
            //return ResponseEntity.ok(repo.findById(id));
    		return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id));
		} catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
		}
    }

    @PutMapping("/{id}")
    public void put(@PathVariable("id") String id, @RequestBody CameraFotografica camera) {
        if (!id.equals(camera.getId())) {
            throw new RuntimeException("IDs da URL e do objeto não conferem!");
        }
        repo.update(camera);
    }


    
}
