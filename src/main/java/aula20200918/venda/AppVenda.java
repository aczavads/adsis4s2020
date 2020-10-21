package aula20200918.venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class AppVenda implements CommandLineRunner {
    @Autowired
    private VendaService service;

    public static void main(String[] args) {
        SpringApplication.run(AppVenda.class, args).close();
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        service.incluirDadosIniciais();
        Pedido pedido1 = service.recuperarPedidoPeloNúmero(1);
        System.out.println("Total do pedido [1]: " + pedido1.getValorTotal() + ", cliente: " + pedido1.getCliente().getNome());
    }



    
}
