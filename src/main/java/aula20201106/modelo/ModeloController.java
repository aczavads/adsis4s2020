package aula20201106.modelo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aula20201106.BaseController;

@RestController
@RequestMapping("/api/modelos")
public class ModeloController extends BaseController<Modelo, ModeloRepository> {

    @GetMapping("/versão")
    public String getVersão() {
        return "v1";
    }
    
}
