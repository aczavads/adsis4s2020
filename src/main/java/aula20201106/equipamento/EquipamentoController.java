package aula20201106.equipamento;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aula20201106.BaseController;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController extends BaseController<Equipamento, EquipamentoRepository> {
     
}
