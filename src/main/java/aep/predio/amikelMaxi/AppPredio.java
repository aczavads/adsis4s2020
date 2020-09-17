package aep.predio.amikelMaxi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppPredio  implements CommandLineRunner{
	@Autowired
	private ControladorPredio controladorPredio;
	public static void main(String[] args) {
		SpringApplication.run(AppPredio.class, args).close();
	}

	@Override
	public void run(String... args) throws Exception {
		
		controladorPredio.criarPredio();
	}

}
