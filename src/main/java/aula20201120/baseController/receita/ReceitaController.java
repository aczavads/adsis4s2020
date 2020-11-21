package aula20201120.baseController.receita;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aula20201120.baseController.BaseController;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController extends BaseController<Receita, ReceitaRepository> {
    
}
