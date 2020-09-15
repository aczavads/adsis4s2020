package caio2020_09_15.forum;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// uma casa pode ter zero ou um dono  
// uma casa pode ter zero ou varias portas 
// uma casa pode ter zero ou varias janelas

@Service
@Transactional
public class SimplificandoService {
    @Autowired
    private EntityManager em;

	public void testarPersistência() {
        em.createQuery("delete from ").executeUpdate();
        em.createQuery("delete from ").executeUpdate();


        Casa casa = new Casa("Germinada");
        Casa casa1 = new Casa("Sobrado");
        
        
        Porta pQuarto = new Porta("Porta de correr");
        Porta pBanheiro = new Porta("Porta do banheiro");
        Janela jSala = new Janela ("Janela da sala");
        Janela jQuarto = new Janela ("Janela do quarto");
        Dono dono = new Dono("Dono da casa", 19);

        dono.addCasa(casa1);
        
        casa.setDono(dono);
        
        casa.addPorta(pQuarto);
        casa.addPorta(pBanheiro);
        casa.addJanela(jSala);
        casa.addJanela(jQuarto);

        casa.setDono(dono);


        em.persist(casa);
	}

}
