package aula20201016.rest;

import java.util.Objects;
import java.util.UUID;

public class CameraFotografica {
    private String id;
    private String modelo;
    private int anoLancamento;
    private TipoSensor tipoSensor;

    public CameraFotografica() {
        id = UUID.randomUUID().toString();
    }

    public CameraFotografica(String modelo, int anoLancamento, TipoSensor tipoSensor) {
        this();
        this.modelo = modelo;
        this.anoLancamento = anoLancamento;
        this.tipoSensor = tipoSensor;
    }

    public CameraFotografica(String id, String modelo, int anoLancamento, TipoSensor tipoSensor) {
        this(modelo, anoLancamento, tipoSensor);
        this.id = id;
	}

	public String getId() {
        return id;
    }
    public String getModelo() {
        return modelo;
    }
    public int getAnoLancamento() {
        return anoLancamento;
    }
    public TipoSensor getTipoSensor() {
        return tipoSensor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CameraFotografica)) {
            return false;
        }
        CameraFotografica cameraFotografica = (CameraFotografica) o;
        return Objects.equals(id, cameraFotografica.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    
}
