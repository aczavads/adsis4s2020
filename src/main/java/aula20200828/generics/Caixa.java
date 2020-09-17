package aula20200828.generics;

public class Caixa<TIPO_DA_COISA> {
	private TIPO_DA_COISA coisa;
	
	public void colocar(TIPO_DA_COISA coisa) {
		if (temCoisa()) {
			throw new RuntimeException("Caixa já tem coisa!");
		}
		this.coisa = coisa;
	}
	
	public TIPO_DA_COISA retirarCoisa() {
		TIPO_DA_COISA coisaAux = this.coisa;
		this.coisa = null;
		return coisaAux;
	}
	
	public boolean temCoisa() {
		return this.coisa != null;
	}

}
