package aula20201027.pessoa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PessoaRepository extends JpaRepository<Pessoa, String> {

    @Query("select f from Fisica f")
    List<Fisica> findAllFisica();
    
    @Query("select j from Juridica j")
	List<Juridica> findAllJuridica();
    
}
