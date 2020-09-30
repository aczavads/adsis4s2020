package exemploDeAssocoaciaoDeClasse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class AppExemploDeAssociacaoClasse implements CommandLineRunner{
	@Autowired
	private ControladorClasses controlador;
	public static void main(String[] args) {
		SpringApplication.run(AppExemploDeAssociacaoClasse.class, args).close();
		
	}
	@Override
	public void run(String... args) throws Exception {
		controlador.crearObjetos();
		
	}
	

}
