package aula20201110.consumindoRestApi;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import aula20201027.produto.Produto;

public class AppConsumindoRestApi implements CommandLineRunner {

    public static void main(String[] args) {
        //SpringApplication.run(AppConsumindoRestApi.class, args).close();
        new SpringApplicationBuilder(AppConsumindoRestApi.class)
            .web(WebApplicationType.NONE)
            .run(args)
            .close();
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("= PREÇO MÉDIO DOS PRODUTOS ====================== ");
        imprimirMédiaDoPreçoAtualDosProdutos();

        String id = criarNovoProduto("Novo", 125.13);
        
        System.out.println("= PELO ID ======================================= ");
        imprimirProdutoPeloId("1329b8da-3d6a-4da9-af50-ebf8188c2a5d");
        
        System.out.println("= TODOS ========================================= ");
        imprimirTodosOsProdutos();

        System.out.println("= PREÇO MÉDIO DOS PRODUTOS ====================== ");
        imprimirMédiaDoPreçoAtualDosProdutos();


        excluirProdutoPeloId(id);
    }

    private void imprimirMédiaDoPreçoAtualDosProdutos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BigDecimal> response = restTemplate.getForEntity(
            "http://localhost:8080/api/produtos/média-do-preco-atual", 
            BigDecimal.class); 
        System.out.println(response.getBody());
    }

    private void excluirProdutoPeloId(String id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8080/api/produtos/" + id);
    }
    private String criarNovoProduto(String nome, double precoAtual) {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", nome);
        dados.put("precoAtual", precoAtual);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responsePost = restTemplate.postForEntity(
            "http://localhost:8080/api/produtos", dados, String.class);
        System.out.println("Status="+responsePost.getStatusCode());
        System.out.println(responsePost.getBody());
        return responsePost.getBody();
    }
    public void imprimirTodosOsProdutos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Produto[]> responseProdutos = restTemplate.getForEntity(
            "http://localhost:8080/api/produtos", 
            Produto[].class); 
        for (Produto p : responseProdutos.getBody()) {
            System.out.println(p);
        }
    }
    public void imprimirProdutoPeloId(String id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Produto> responseProduto = restTemplate.getForEntity(
            "http://localhost:8080/api/produtos/" + id, 
            Produto.class); 
        System.out.println("Status=" + responseProduto.getStatusCode());
        System.out.println(responseProduto.getBody());
    }
}
