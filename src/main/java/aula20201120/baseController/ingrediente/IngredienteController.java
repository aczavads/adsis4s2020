package aula20201120.baseController.ingrediente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aula20201120.baseController.BaseController;

@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController extends BaseController<Ingrediente, IngredienteRepository> {

    
}
