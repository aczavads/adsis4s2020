package rest.api.camara.forum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;

public class CamaraNaMemoria {
	private Map<String, Camara> dados = new HashMap<>();
	
	public CamaraNaMemoria() {
		Camara asus = new Camara("Asus XG8", 2020,TipoSensor.FULL_FRAME);
		dados.put(asus.getId(), asus);
	}

	public List<Camara> findAll() {
		return new ArrayList<> (dados.values());
	}

	public void save(Camara novaCamara) {
		if(dados.containsKey(novaCamara.getId())) {
			throw new RuntimeException("Erro, chave duplicada. O objeto já existe");
		}
		dados.put(novaCamara.getId(), novaCamara);
		
	}

	public void deletdById(String id) {
		if(!dados.containsKey(id)) {
			throw new RuntimeException("O referente objeto não exixte");
		}
		dados.remove(id);
		
	}

	public Camara findById(String id) {
		if(!dados.containsKey(id)) {
			throw new RuntimeException("Objeto não existe ");
		}
		return dados.get(id);
	}

	public void update(Camara camara) {
		if(!dados.containsKey(camara.getId())) {
			throw new RuntimeException("Objeto nao existe ["+ camara.getId()+ "]");
		}
		dados.remove(camara.getId());
		dados.put(camara.getId(), camara);
		
	}

	

}
