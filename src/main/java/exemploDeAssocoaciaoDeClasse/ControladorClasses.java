package exemploDeAssocoaciaoDeClasse;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ControladorClasses {
	
	@Autowired
	private EntityManager man;

	public void crearObjetos() {
		Pessoa amikel = new Pessoa("Amikel Maxi","703845-46");
		
		Carro rav4 = new Carro("Toyota Rav4","CJL-1715",2019);
		Carro gol = new Carro("Novo gol Palio","CJL-0715",2019);
		Carro nissan = new Carro("Nissan Fontier","CJL-1315",2017);
		
		
		amikel.addCarro(rav4);
		amikel.addCarro(gol);
		amikel.addCarro(nissan);
		
		man.persist(amikel);
	}

}
