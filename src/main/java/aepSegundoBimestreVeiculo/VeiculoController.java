package aepSegundoBimestreVeiculo;

import java.util.List;

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

import aula20201016.rest.CameraFotografica;
import aula20201027.pedido.Pedido;
import aula20201027.produto.IdsDiveregentesException;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoRepository dado;
	
	@GetMapping
	public List<Veiculo> getAll(){
		return dado.findAll();
	}
	
	@PostMapping
	public String post(@RequestBody Veiculo veiculo) {
		if(dado.findById(veiculo.getId()).isPresent()) {
			throw new ChaveDuplicadoException();
		}
		veiculo = dado.save(veiculo);
		return veiculo.getId();
	}
	
	 @PutMapping("/{id}")
	    public void put(@PathVariable String id, @RequestBody Veiculo ve) {
	        if (!id.equals(ve.getId())) {
	            throw new NotFoundOnPutObject();
	        }
	        dado.save(ve);
	    }
	
	 @GetMapping("/{id}")    
	 public ResponseEntity<Veiculo> getById(@PathVariable String id) {
		 try {
			return ResponseEntity.status(HttpStatus.OK).body(dado.findById(id).get());
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
        
	 }
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void > delete(@PathVariable("id") String id) {
		 try {
			 dado.deleteById(id);
			 return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
		 
	 }
	
}
