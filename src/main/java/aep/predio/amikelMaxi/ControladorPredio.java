package aep.predio.amikelMaxi;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ControladorPredio {

	@Autowired
	private EntityManager manager;
	public void criarPredio() {
		
		Predio predio1 = new Predio("Predio 1 do bloco A","Liberado",2010);
		
		Apartamento ap1 = new Apartamento("Apartamento 3308 piso 1 bloco A");
		Apartamento ap2 = new Apartamento("Apartamento 3320 piso 1 bloco A");
		Apartamento ap3 = new Apartamento("Apartamento 3348 piso 1 bloco C");
		Apartamento ap4 = new Apartamento("Apartamento 3008 piso 5 bloco F");
		
		Endereco enderecoPredio1 = new Endereco("Vereador Joaquim Pereira de Castro","239A","Vila Santo Antônio");
		
		Garagem garAp1 = new Garagem("3308A","Piso 1");
		Garagem garAp2 = new Garagem("3308B","Piso 1");
		
		ap1.setGaragensApartamento(garAp2);
		ap1.setGaragensApartamento(garAp1);
		
		predio1.setApartamento(ap1);
		predio1.setApartamento(ap2);
		predio1.setApartamento(ap3);
		predio1.setApartamento(ap3);
		
		predio1.setEndereco(enderecoPredio1);
		
		manager.persist(predio1);
		System.out.println("");
		System.out.println("Predio: "+predio1.getDescripcao()+" tem "+predio1.getApartamentos().size()+" apartamentos");
		System.out.println("Endereço:");
		System.out.println("Rua: "+ predio1.getEnderecoApartamento().getLogaduro());
		System.out.println("Número: "+predio1.getEnderecoApartamento().getNumero());
		System.out.println("Bairro: "+predio1.getEnderecoApartamento().getBairro());
		for (int i = 0; i < predio1.getApartamentos().size(); i++) {
			System.out.println("");
			System.out.println("*");
			System.out.println("Apartamento: "+predio1.getApartamentos().get(i).getNumero());
			System.out.println("Descrição: "+predio1.getApartamentos().get(i).getDescripcao());
			System.out.println(">>>>>>> fim da lista");
		}
		
		
	}

}