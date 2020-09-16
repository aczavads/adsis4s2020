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
        em.createQuery("update Pais p set p.distritoFederal = null").executeUpdate();        
        em.createQuery("delete from Estado").executeUpdate();
        em.createQuery("delete from Pais").executeUpdate();


        Pais brasil = new Pais("Brasil");

        Estado paraná1 = new Estado("Paraná1");
        Estado paraná2 = new Estado("Paraná2");
        Estado paraná3 = new Estado("Paraná3");
        Estado paraná4 = new Estado("Paraná4");
        System.out.println(paraná1.getId());
        System.out.println(paraná2.getId());
        System.out.println(paraná3.getId());
        System.out.println(paraná4.getId());
        Estado acre = new Estado("Acre");
        Estado pernambuco = new Estado("Pernambuco");
        Estado df = new Estado("DF");

        brasil.addEstado(paraná1);
        brasil.addEstado(paraná2);
        brasil.addEstado(paraná3);
        brasil.addEstado(paraná4);
        brasil.addEstado(acre);
        brasil.addEstado(pernambuco);
        brasil.addEstado(df);

        brasil.setDistritoFederal(df);


        em.persist(brasil);
	}

}
