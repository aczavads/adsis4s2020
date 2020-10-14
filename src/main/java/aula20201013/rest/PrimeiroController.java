package aula20201013.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aula20200908.associaçõesPersistentes.Fisica;
import aula20200908.associaçõesPersistentes.Pessoa;

@RestController
@RequestMapping("/primeiro")
public class PrimeiroController {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @GetMapping("/dataAtual")
    public String getDataAtual() {
        return sdf.format(new Date());
    }

    @GetMapping("/pessoa")
    public Pessoa getPessoa() {
        return new Fisica("Fulano de Tal", "554.544.544-44", "5.778.458-1");
    }

    @PostMapping("/pessoaFisica")
    public void criarPessoa(@RequestBody Fisica nova) {
        System.out.println(nova.toString());
    }

}
