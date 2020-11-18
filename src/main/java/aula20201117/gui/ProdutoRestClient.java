package aula20201117.gui;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProdutoRestClient {

    public ResponseEntity<String> postProduto(String nome, double precoAtual) {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", nome);
        dados.put("precoAtual", precoAtual);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(
            "http://localhost:8080/api/produtos", dados, String.class);
        return response;
    }


}
