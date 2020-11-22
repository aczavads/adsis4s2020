package aula20201120.baseController.ingrediente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, String> {
    
}
