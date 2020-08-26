package aula20200825.tryWithResources;

public class Cofre implements AutoCloseable {
	
	public void open() {
		System.out.println("Cofre aberto!");
		throw new RuntimeException("Erro no cofre!");
	}

	@Override
	public void close() {
		System.out.println("Cofre fechado.");
	}

}
