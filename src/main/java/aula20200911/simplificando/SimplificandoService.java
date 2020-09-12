package aula20200911.simplificando;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SimplificandoService {
    @Autowired
    private EntityManager em;

	public void testarPersistência() {
        em.createQuery("delete from Estado").executeUpdate();
        em.createQuery("delete from Pais").executeUpdate();


        Pais brasil = new Pais("Brasil");

        Estado paraná = new Estado("Paraná");
        Estado acre = new Estado("Acre");
        Estado pernambuco = new Estado("Pernambuco");
        Estado df = new Estado("DF");

        brasil.addEstado(paraná);
        brasil.addEstado(acre);
        brasil.addEstado(pernambuco);
        brasil.addEstado(df);

        brasil.setDistritoFederal(df);


        em.persist(brasil);
	}

}
