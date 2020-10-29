package rest.api.camara.forum;

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
@RequestMapping("/api/camarafotograficas")
public class CamaraController {
	@Autowired
	private CamaraNaMemoria repo;
	
	@GetMapping
	private List<Camara> gatAll(){
		return repo.findAll();
	}

	@PostMapping
	private String post(@RequestBody Camara nova) {
		repo.save(nova);
		return nova.getId();
	}
	
	@DeleteMapping("/{id}")
	private void delete(@PathVariable("id") String id) {
		repo.deletdById(id);
	}
	
	@GetMapping("/{id}")
	private Camara getCamara(@PathVariable("id") String id) {
		return repo.findById(id);
	}
	
	@PutMapping("/{id}")
	private void put(@PathVariable("id") String id,@RequestBody Camara camara) {
		if(id.equals(camara.getId())) {
			throw new RuntimeException("ID de objeto não localizado");
		}
		repo.update(camara);
	}
}
