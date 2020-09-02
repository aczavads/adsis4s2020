package aula20200828.generics;

import java.util.ArrayList;
import java.util.List;

import aula20200825.abstractRepository.Pet;

public class AppCoisa {
	public static void main(String[] args) {
		Caixa<ChaveDoCarro> minhaCaixaDeChaves = new Caixa<>();
		
		minhaCaixaDeChaves.colocar(new Pet("Fiel"));
		minhaCaixaDeChaves.colocar(new ChaveDoCarro());
		
		System.out.println(minhaCaixaDeChaves.temCoisa());
		
		List<Pet> lista = new ArrayList<>();
		lista.add(new Pet("Fiel"));
		lista.add(new ChaveDoCarro());
	}

}
