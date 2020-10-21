package aula20201020.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CameraFotograficaRepositoryInMemory {
    private Map<String, CameraFotografica> dados = new HashMap<>();

    public CameraFotograficaRepositoryInMemory() {
        CameraFotografica t3i = new CameraFotografica("84c4454f-e7bc-48b8-b72c-3c22351edd54", "Canon T3i",2008, TipoSensor.APSC);
        CameraFotografica xt30 = new CameraFotografica("0c8f00ce-e3c5-4d00-8620-32a342a8f435", "Fujifilm X-T30",2018, TipoSensor.APSC);
        dados.put(t3i.getId(), t3i);
        dados.put(xt30.getId(), xt30); 
    }

	public List<CameraFotografica> findAll() {
		return new ArrayList<>(dados.values());
	}

	public void save(CameraFotografica newInstance) {
        if (dados.containsKey(newInstance.getId())) {
            throw new RuntimeException("ID duplicado! Já existe uma câmera com o id [" + newInstance.getId() + "]");
        }
        dados.put(newInstance.getId(), newInstance);
	}

	public void deleteById(String id) {
        if (!dados.containsKey(id)) {
            throw new RuntimeException("Não existe uma câmera com o id [" + id + "]");
        }
        dados.remove(id);
	}

	public CameraFotografica findById(String id) {
        if (!dados.containsKey(id)) {
            throw new NotFoundException("Não existe uma câmera com o id [" + id + "]");
        }
		return dados.get(id);
	}

	public void update(CameraFotografica updatedInstance) {
        if (!dados.containsKey(updatedInstance.getId())) {
            throw new RuntimeException("Não existe uma câmera com o id [" + updatedInstance.getId() + "]");
        }
        dados.remove(updatedInstance.getId());
        dados.put(updatedInstance.getId(), updatedInstance);
	}

}
