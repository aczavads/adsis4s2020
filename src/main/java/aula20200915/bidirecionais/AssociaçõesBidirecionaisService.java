package aula20200915.bidirecionais;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssociaçõesBidirecionaisService {
    @Autowired
    private EntityManager em;

	public void testarPersistência() {
        Estado acre = em.createQuery("from Estado e where e.nome = 'Acre'", Estado.class).getSingleResult();        
        System.out.println(acre.getPais());
        System.out.println(">>>>>>>>>>>>> estados do país");
        for (Estado e : acre.getPais().getEstados()) {
            System.out.println(e.getNome());
        }
	}

}
