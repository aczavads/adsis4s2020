package caio2020_09_15.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppSimplificando implements CommandLineRunner {
    @Autowired
    private SimplificandoService service;


    public static void main(String[] args) {
        SpringApplication.run(AppSimplificando.class, args).close();
    }

    @Override
    public void run(String... args) throws Exception {
        service.testarPersistência();
    }

    
}
