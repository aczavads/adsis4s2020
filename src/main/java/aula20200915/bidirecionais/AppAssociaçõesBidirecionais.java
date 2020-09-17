package aula20200915.bidirecionais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppAssociaçõesBidirecionais implements CommandLineRunner {
    @Autowired
    private AssociaçõesBidirecionaisService service;


    public static void main(String[] args) {
        SpringApplication.run(AppAssociaçõesBidirecionais.class, args).close();
    }

    @Override
    public void run(String... args) throws Exception {
        service.testarPersistência();
    }

}
