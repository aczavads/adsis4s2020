package rest.api.camara.forum;

import java.util.Objects;
import java.util.UUID;

import aula20201016.rest.CameraFotografica;

public class Camara {

	private String id;
	private int anoLancamento;
	private String modelo;
	private TipoSensor tipoSensor;
	
	public Camara() {
		id = UUID.randomUUID().toString();
	}
	
	public Camara(String modelo, int anoLancamento, TipoSensor tipoSensor) {
		this();
		this.modelo = modelo;
		this.anoLancamento = anoLancamento;
		this.tipoSensor = tipoSensor;
		
	}
	
	public Camara(String id, String modelo, int anoLancamento, TipoSensor tipoSensor) {
		this(modelo,anoLancamento,tipoSensor);
		this.id = id;
	}
	public int getAnoLancamento() {
		return anoLancamento;
	}
	
	public String getId() {
		return id;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public TipoSensor getTipoSensor() {
		return tipoSensor;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Camara)) {
			return false;
		}
			
		Camara camara = (Camara) obj;
		return Objects.equals(id, camara.id);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
